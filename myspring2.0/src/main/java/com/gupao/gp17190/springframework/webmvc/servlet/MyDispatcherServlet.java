package com.gupao.gp17190.springframework.webmvc.servlet;

import com.gupao.gp17190.springframework.beans.annotation.MyController;
import com.gupao.gp17190.springframework.beans.annotation.MyRequestMapping;
import com.gupao.gp17190.springframework.context.support.MyApplicationContext;
import com.gupao.gp17190.springframework.web.servlet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * @Author laihui
 * @Date 2019/4/19
 * @Desc
 * @Version 1.0
 **/
public class MyDispatcherServlet extends MyFrameworkServlet {
//    private static final Logger logger = LoggerFactory.getLogger(MyDispatcherServlet.class);

    private List<MyHandlerMapping> handlerMappings = new ArrayList<MyHandlerMapping>();

    private Map<MyHandlerMapping, MyHandlerAdapter> handlerAdapters = new ConcurrentHashMap<MyHandlerMapping, MyHandlerAdapter>();

    private List<MyViewResolver> viewResolvers = new ArrayList<MyViewResolver>();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            this.doDispatch(req, resp);
        } catch (Exception e) {
            resp.getWriter().write("500 Exception,Details:\r\n" + Arrays.toString(e.getStackTrace()).replaceAll("\\[|\\]", "").replaceAll(",\\s", "\r\n"));
            e.printStackTrace();
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        try {
            // 1.查找HandlerMapping
            MyHandlerMapping handler = getHandlerMapping(req);
            if (handler == null) {
                processDispatchResult(req, resp, new MyModelAndView("404"));
            }

            // 2.得到HandlerAdapter
            MyHandlerAdapter ha = getHandlerAdapter(handler);

            // 3.调用处理
            MyModelAndView mv = ha.handle(req, resp, handler);

            // 4.输出结果到浏览器
            processDispatchResult(req, resp, mv);
        } catch (Exception e) {
            // 异常页面
            processDispatchResult(req, resp, new MyModelAndView("500"));
        }
    }

    private void processDispatchResult(HttpServletRequest req,
                                       HttpServletResponse resp, MyModelAndView mv) throws Exception {
        if (mv == null || this.viewResolvers.isEmpty()) return;

        for (MyViewResolver viewResolver : this.viewResolvers) {
            MyView view = viewResolver.resolveViewName(mv.getViewName(), null);
            view.render(mv.getModel(), req, resp);

            return;
        }
    }

    private MyHandlerAdapter getHandlerAdapter(MyHandlerMapping handler) {
        if (this.handlerAdapters.isEmpty())
            return null;

        MyHandlerAdapter ha = this.handlerAdapters.get(handler);
        if (ha.support(handler))
            return ha;

        return null;
    }

    private MyHandlerMapping getHandlerMapping(HttpServletRequest req) {
        if (this.handlerMappings.isEmpty())
            return null;

        String url = req.getRequestURI();
        String content = req.getContextPath();
        url = url.replace(content, "").replaceAll("/+", "/");

        for (MyHandlerMapping handlerMapping : this.handlerMappings) {
            if (handlerMapping.getPattern().matcher(url).matches())
                return handlerMapping;
        }

        return null;
    }

    @Override
    protected void onRefresh(MyApplicationContext context) {
        initStrategies(context);
    }

    private void initStrategies(MyApplicationContext context) {
        // 初始化文件上传
        initMultipartResolver(context);
        // 初始化本地化
        initLocaleResolver(context);
        // 初始化主题
        initThemeResolver(context);
        // 初始化HandlerMapping，需要实现
        initHandlerMappings(context);
        // 初始化HandlerAdapter，需要实现
        initHandlerAdapters(context);
        // 初始化异常解析
        initHandlerExceptionResolvers(context);
        // 初始化请求到视图转换
        initRequestToViewNameTranslator(context);
        // 初始化视图解析，需要实现
        initViewResolvers(context);
        // 初始化闪存
        initFlashMapManager(context);
    }

    private void initMultipartResolver(MyApplicationContext context) {

    }

    private void initLocaleResolver(MyApplicationContext context) {

    }

    private void initThemeResolver(MyApplicationContext context) {

    }

    private void initHandlerMappings(MyApplicationContext context) {
        String[] beanNames = context.getBeanDefinitionNames();
        try {
            for (String beanName : beanNames) {
                Object controller = context.getBean(beanName);
                Class<?> clazz = controller.getClass();

                if (!clazz.isAnnotationPresent(MyController.class)) continue;

                String baseUrl = "";
                if (clazz.isAnnotationPresent(MyRequestMapping.class)) {
                    MyRequestMapping requestMapping = clazz.getAnnotation(MyRequestMapping.class);
                    baseUrl = requestMapping.value();
                }

                for (Method method : clazz.getMethods()) {
                    if (!method.isAnnotationPresent(MyRequestMapping.class)) continue;

                    MyRequestMapping requestMapping = method.getAnnotation(MyRequestMapping.class);
                    // 拼接URL正则
                    String regex = ("/" + baseUrl + "/" + requestMapping.value().replaceAll("\\*", ".*")).replaceAll("/+", "/");
                    Pattern pattern = Pattern.compile(regex);

                    this.handlerMappings.add(new MyHandlerMapping(controller, method, pattern));

//                    logger.info("mapped " + regex + "," + method);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initHandlerAdapters(MyApplicationContext context) {
        for (MyHandlerMapping handlerMapping : this.handlerMappings) {
            this.handlerAdapters.put(handlerMapping, new MyHandlerAdapter());
        }

    }

    private void initHandlerExceptionResolvers(MyApplicationContext context) {

    }

    private void initRequestToViewNameTranslator(MyApplicationContext context) {

    }

    private void initViewResolvers(MyApplicationContext context) {
        // 从配置中获取静态资源的根路径
        String templateRoot = context.getConfig().getProperty("templateRoot");
//        String templateRootPath = this.getClass().getClassLoader().getResource(templateRoot).getFile();
        // 此处简化处理，仅支持html解析
        this.viewResolvers.add(new MyViewResolver(templateRoot));

    }

    private void initFlashMapManager(MyApplicationContext context) {

    }
}
