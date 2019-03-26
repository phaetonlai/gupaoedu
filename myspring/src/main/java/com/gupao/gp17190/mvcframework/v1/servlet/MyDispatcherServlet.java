package com.gupao.gp17190.mvcframework.v1.servlet;

import com.gupao.gp17190.mvcframework.annotation.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author laihui
 * @version 1.0
 * @date 2019/3/26
 * @description
 **/
public class MyDispatcherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 任务分派
        doDispatch(req, resp);
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) {
        // 获取url对应的处理器
        Handler handler = findHandler(req);

        // 处理请求
        doRequest(req, resp, handler);
    }

    private void doRequest(HttpServletRequest req, HttpServletResponse resp, Handler handler) {
        if (handler == null) {
            try {
                resp.getWriter().print("404 Not Found!");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        // 解析方法调用的参数值列表
        Object[] paramValues = parseParamValues(req, resp, handler);

        // 调用并返回
        invoke(resp, handler, paramValues);
    }

    private void invoke(HttpServletResponse resp, Handler handler, Object[] paramValues) {
        try {
            Object result = handler.getMethod().invoke(handler.getController(), paramValues);
            if (result == null || result instanceof Void) return;

            resp.getWriter().write(result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Object[] parseParamValues(HttpServletRequest req, HttpServletResponse resp, Handler handler) {
        Class[] paramTypes = handler.getParamTypes();
        Object[] paramValues = new Object[paramTypes.length];

        Map<String, String[]> paramMap = req.getParameterMap();
        for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
            String value = Arrays.toString(entry.getValue()).replaceAll("\\[|\\]", "").replaceAll("\\s", ",");
            String paramName = entry.getKey();
            if (!handler.getParanNameIndexMap().containsKey(paramName)) continue;
            int index = handler.getParanNameIndexMap().get(paramName);
            paramValues[index] = convert(paramTypes[index], value);
        }

        // 单独处理request,response
        if (handler.getParanNameIndexMap().containsKey(HttpServletRequest.class.getSimpleName())) {
            int index = handler.getParanNameIndexMap().get(HttpServletRequest.class.getSimpleName());
            paramValues[index] = req;
        }
        if (handler.getParanNameIndexMap().containsKey(HttpServletResponse.class.getSimpleName())) {
            int index = handler.getParanNameIndexMap().get(HttpServletResponse.class.getSimpleName());
            paramValues[index] = resp;
        }

        return paramValues;
    }

    private Object convert(Class paramType, String value) {
        if (paramType == Integer.class) return Integer.parseInt(value);

        if (paramType == Double.class) return Double.parseDouble(value);

        return value;
    }

    private Handler findHandler(HttpServletRequest req) {
        if (handlerMapping.isEmpty()) return null;
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replaceAll(contextPath, "").replaceAll("/+", "/");

        for (Handler handler : handlerMapping) {
            Matcher matcher = handler.getPattern().matcher(url);
            if (!matcher.matches()) continue;
            return handler;
        }

        return null;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        // 1.读取配置文件
        loadProperties(config.getInitParameter("contextConfigLocation"));

        // 2.扫描根路径下的class文件
        scanClasses(properties.getProperty("basePackage"));

        // 3.初始化IOC容器
        initIocContainer();

        // 4.依赖注入
        doDependencyInjection();

        // 5.初始化ApplicationContext容器
        initHandlerMapping();

    }

    private Properties properties = new Properties();

    private void loadProperties(String contextConfigLocation) {
        InputStream fis = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
        try {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 将根路径下的类全称放入list，后续完成bean容器初始化以及依赖注入使用
    private List<String> classNameList = new ArrayList<String>();

    private void scanClasses(String basePackage) {
        //
        URL url = this.getClass().getClassLoader().getResource(basePackage.replaceAll("\\.", "/"));
        File basePath = new File(url.getFile());
        for (File file : basePath.listFiles()) {
            if (!file.isFile()) {
                scanClasses(basePackage + "." + file.getName());
            } else {
                if (!file.getName().endsWith(".class")) continue;
                classNameList.add(basePackage + "." + file.getName().replace(".class", ""));
            }
        }

    }

    private Map<String, Object> beanContainer = new HashMap<String, Object>();

    private void initIocContainer() {
        // 根路径下如果为空，直接返回
        if (classNameList.isEmpty()) return;
        try {
            for (String className : classNameList) {
                Class clazz = Class.forName(className);
                if(clazz.isAnnotationPresent(MyController.class)) {
                    String beanName = toLowerFirstCase(clazz.getSimpleName());
                    Object instance = clazz.newInstance();

                    beanContainer.put(beanName, instance);
                } else if (clazz.isAnnotationPresent(MyService.class)) {
                    MyService serviceAnnotation = (MyService) clazz.getAnnotation(MyService.class);
                    String beanName = serviceAnnotation.value();
                    if ("".equals(beanName.trim())) {
                        beanName = toLowerFirstCase(clazz.getSimpleName());
                    }
                    Object instance = clazz.newInstance();

                    beanContainer.put(beanName, instance);

                    // 如果service注解中没有指定beanName，则根据类实现的接口自动生成对应的bean
                    for (Class anInterface : clazz.getInterfaces()) {
                        // TODO 一个接口有多个实现的时情况暂未处理
                        beanName = toLowerFirstCase(anInterface.getSimpleName());
                        if (beanContainer.containsKey(beanName)) {
                            throw new Exception("bean " + beanName + " has already existed!");
                        }
                        beanContainer.put(beanName, instance);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 将首字母替换成小写
     * @param simpleName
     * @return
     */
    private String toLowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        chars[0] += 32;

        return String.valueOf(chars);
    }

    private void doDependencyInjection() {
        // bean容器如果为空，直接返回
        if (beanContainer.isEmpty()) return;
        try {
            for (Map.Entry<String, Object> entry : beanContainer.entrySet()) {
                Field[] fields = entry.getValue().getClass().getDeclaredFields();
                for (Field field : fields) {
                    if (field.isAnnotationPresent(MyAutowired.class)) {
                        MyAutowired annotation = field.getAnnotation(MyAutowired.class);
                        String beanName = annotation.value();
                        if ("".equals(beanName.trim())) {
                            beanName = toLowerFirstCase(field.getType().getSimpleName());
                        }
                        // 暴力反射，对被MyAutowired注解标记的成员进行依赖注入
                        field.setAccessible(true);
                        field.set(entry.getValue(), beanContainer.get(beanName));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 存放请求路径与处理器的容器
    private List<Handler> handlerMapping = new ArrayList<>();

    private void initHandlerMapping() {
        // bean容器如果为空，直接返回
        if (beanContainer.isEmpty()) return;

        for (Map.Entry<String, Object> entry : beanContainer.entrySet()) {
            Class clazz = entry.getValue().getClass();
            if (!clazz.isAnnotationPresent(MyController.class)) continue;
            if (!clazz.isAnnotationPresent(MyRequestMapping.class)) continue;
            // 先获取控制器上的基本路径
            String baseUrl = ((MyRequestMapping) clazz.getAnnotation(MyRequestMapping.class)).value();
            // 再获取方法上的路径
            for (Method method : clazz.getMethods()) {
                if (!method.isAnnotationPresent(MyRequestMapping.class)) continue;
                String url = method.getAnnotation(MyRequestMapping.class).value();

                String regex = ("/" + baseUrl + "/" + url).replaceAll("/+", "/");
                Pattern pattern = Pattern.compile(regex);

                handlerMapping.add(new Handler(pattern, entry.getValue(), method));
            }
        }

    }

    private class Handler {
        // 请求路径
        private Pattern pattern;

        // 控制器对象
        private Object controller;

        // 方法对象
        private Method method;

        // 方法参数的类型
        private Class[] paramTypes;

        // 方法参数与index的映射关系
        private Map<String, Integer> paranNameIndexMap;


        public Handler(Pattern pattern, Object controller, Method method) {
            this.pattern = pattern;
            this.controller = controller;
            this.method = method;

            this.paramTypes = method.getParameterTypes();

            paranNameIndexMap = new HashMap<>();
            paramNameIndexMapping(method);
        }

        public Pattern getPattern() {
            return pattern;
        }

        public Object getController() {
            return controller;
        }

        public Method getMethod() {
            return method;
        }

        public Class[] getParamTypes() {
            return paramTypes;
        }

        public Map<String, Integer> getParanNameIndexMap() {
            return paranNameIndexMap;
        }

        private void paramNameIndexMapping(Method method) {
            // 方法参数的注解是一个二维数组
            Annotation[][] annotations = method.getParameterAnnotations();
            for (int i = 0; i < annotations.length; i++) {
                for (Annotation annotation : annotations[i]) {
                    if (!(annotation instanceof MyRequestParam)) continue;
                    String paraName = ((MyRequestParam) annotation).value();
                    if (!"".equals(paraName.trim())) {
                        paranNameIndexMap.put(paraName, i);
                    }
                }
            }

            // request,response
            Class[] parameterTypes = method.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                Class parameterType = parameterTypes[i];
                if (parameterType == HttpServletRequest.class
                        || parameterType == HttpServletResponse.class) {
                    paranNameIndexMap.put(parameterType.getSimpleName(), i);
                }
            }

        }
    }
}
