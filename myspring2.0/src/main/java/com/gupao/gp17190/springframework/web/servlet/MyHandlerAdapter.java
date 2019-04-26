package com.gupao.gp17190.springframework.web.servlet;

import com.gupao.gp17190.springframework.beans.annotation.MyRequestMapping;
import com.gupao.gp17190.springframework.beans.annotation.MyRequestParam;
import com.gupao.gp17190.springframework.webmvc.servlet.MyHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author laihui
 * @Date 2019/4/19
 * @Desc
 * @Version 1.0
 **/
public class MyHandlerAdapter {

    public boolean support(Object o) {
        return (o instanceof MyHandlerMapping);
    }

    public MyModelAndView handle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        MyHandlerMapping handlerMapping = (MyHandlerMapping) handler;

        // 参数名称和参数位置的对应关系
        Map<String, Integer> paramIndexMapping = new HashMap<String, Integer>(64);
        // 先处理HttpServletRequest，HttpServletResponse之外的参数
        Annotation[][] annotations = handlerMapping.getMethod().getParameterAnnotations();
        for (int i = 0; i < annotations.length; i++) {
            for (Annotation annotation : annotations[i]) {
                if (annotation instanceof MyRequestParam) {
                    String paramName = ((MyRequestParam) annotation).value();
                    if (paramName != null && !"".equals(paramName.trim()))
                        paramIndexMapping.put(paramName, i);
                }
            }
        }
        // 处理HttpServletRequest，HttpServletResponse参数
        Class<?>[] parameterTypes = handlerMapping.getMethod().getParameterTypes();
        for (int i = 0; i < parameterTypes.length; i++) {
            if ((parameterTypes[i] == HttpServletRequest.class) || (parameterTypes[i] == HttpServletResponse.class))
                paramIndexMapping.put(parameterTypes[i].getName(), i);
        }

        // 请求的参数
        Map<String, String[]> paramMap = req.getParameterMap();

        // 方法调用的参数值列表
        Object[] paramValues = new Object[parameterTypes.length];
        for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
            if (!paramIndexMapping.containsKey(entry.getKey())) continue;

            String value = Arrays.toString(entry.getValue()).replaceAll("\\[|\\]", "")
                    .replaceAll("\\s", ",");

            int index = paramIndexMapping.get(entry.getKey());
            paramValues[index] = castValue(value, parameterTypes[index]);
        }

        // 单独处理HttpServletRequest，HttpServletResponse
        if (paramIndexMapping.containsKey(HttpServletRequest.class.getName())) {
            paramValues[paramIndexMapping.get(HttpServletRequest.class.getName())] = req;
        }
        if (paramIndexMapping.containsKey(HttpServletResponse.class.getName())) {
            paramValues[paramIndexMapping.get(HttpServletResponse.class.getName())] = resp;
        }

        // 动态调用并返回
        Object result = handlerMapping.getMethod().invoke(handlerMapping.getController(), paramValues);
        if ((result == null) || (result instanceof Void))
            return null;
        // 如果方法返回值是ModelAndView，返回具体对象
        if (handlerMapping.getMethod().getReturnType() == MyModelAndView.class)
            return (MyModelAndView) result;

        return null;
    }

    private Object castValue(String value, Class<?> type) {
        if (type == Integer.class)
            return Integer.parseInt(value);
        if (type == Long.class)
            return Long.parseLong(value);
        if (type == Double.class)
            return Double.parseDouble(value);
        return value;
    }
}
