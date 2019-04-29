package com.gupao.gp17190.springframework.aop.support;

import com.gupao.gp17190.springframework.aop.aspect.MyAfterReturningAdviceInterceptor;
import com.gupao.gp17190.springframework.aop.aspect.MyAfterThrowingAdviceInterceptor;
import com.gupao.gp17190.springframework.aop.aspect.MyMethodBeforeAdviceInterceptor;
import com.gupao.gp17190.springframework.aop.config.MyAopConfig;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author laihui
 * @Date 2019/4/28
 * @Desc
 * @Version 1.0
 **/
public class MyAdvisedSupport {
    private Class<?> targetClass;

    private Object target;

    private MyAopConfig config;

    private Pattern pointCutClassPattern;

    // 方法对象，增强链表的映射
    private transient Map<Method, List<Object>> methodCache;

    public MyAdvisedSupport(MyAopConfig config) {
        this.config = config;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class<?> targetClass) {
        this.targetClass = targetClass;
        parse();
    }

    private void parse() {
        String pointCut = this.config.getPointCut()
                .replaceAll("\\.", "\\\\.")
                .replaceAll("\\\\.\\*", ".*")
                .replaceAll("\\(", "\\\\(")
                .replaceAll("\\)", "\\\\)");
        //
        String pointCutForClassRegex = pointCut.substring(0, pointCut.lastIndexOf("\\(") - 4);
        this.pointCutClassPattern = Pattern.compile("class " + pointCutForClassRegex.substring(
                pointCutForClassRegex.lastIndexOf(" ") + 1));

        this.methodCache = new HashMap<>(64);
        Pattern pattern = Pattern.compile(pointCut);

        try {
            Class<?> aspectClass = Class.forName(this.config.getAspectClass());
            Map<String, Method> aspectMethods = new HashMap<>(128);
            //
            for (Method method : aspectClass.getMethods()) {
                String methodString = method.toString();
                if (methodString.contains("throws")) {
                    methodString = methodString.substring(0, methodString.lastIndexOf("throws")).trim();
                }
                Matcher matcher = pattern.matcher(methodString);
                if (matcher.matches()) {
                    //
                    List<Object> advices = new LinkedList<>();
                    // 前置
                    if (!(null == this.config.getAspectBefore() || ("".equals(this.config.getAspectBefore())))) {
                        advices.add(new MyMethodBeforeAdviceInterceptor(aspectMethods.get(this.config.getAspectBefore()),
                                aspectClass.newInstance()));
                    }
                    // 后置
                    if (!(null == this.config.getAspectAfter() || ("".equals(this.config.getAspectAfter())))) {
                        advices.add(new MyAfterReturningAdviceInterceptor(aspectMethods.get(this.config.getAspectAfter()),
                                aspectClass.newInstance()));
                    }
                    // 异常
                    if (!(null == this.config.getAspectAfterThrow() || ("".equals(this.config.getAspectAfterThrow())))) {
                        MyAfterThrowingAdviceInterceptor interceptor = new MyAfterThrowingAdviceInterceptor(
                                aspectMethods.get(this.config.getAspectAfterThrow()),
                                aspectClass.newInstance());
                        interceptor.setThrowingName(this.config.getAspectAfterThrowingName());
                        advices.add(interceptor);
                    }

                    methodCache.put(method, advices);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Map<Method, List<Object>> getMethodCache() {
        return methodCache;
    }

    public List<Object> getInterceptorsAndDynamicInterceptionAdvise(Method method, Class<?> targetClass) throws Exception {
        List<Object> cached = methodCache.get(method);
        if (cached == null) {
            Method m = targetClass.getMethod(method.getName(), method.getParameterTypes());
            cached = methodCache.get(m);
            // 兼容代理方法
            methodCache.put(method, cached);
        }

        return cached;
    }

    public boolean pointCutMatch() {
        return this.pointCutClassPattern.matcher(this.targetClass.toString()).matches();
    }
}
