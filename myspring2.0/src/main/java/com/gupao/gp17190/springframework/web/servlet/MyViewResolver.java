package com.gupao.gp17190.springframework.web.servlet;

import java.io.File;
import java.util.Locale;

/**
 * @author laihui
 * @version 1.0
 * @date 2019/4/24
 * @description
 **/
public class MyViewResolver {
    private static final String DEFALUT_TEMPLATE_SUFFIX = ".html";

    private File templateRootDir;

    public MyViewResolver(String templateRoot) {
        String templateRootPath =this.getClass().getClassLoader().getResource(templateRoot).getFile();
        this.templateRootDir = new File(templateRootPath);
    }

    public MyView resolveViewName(String viewName, Locale locale) throws Exception {
        if (viewName == null || "".equals(viewName))
            return null;

        String filePath = this.templateRootDir.getPath() + "/";
        filePath += (viewName.toLowerCase().endsWith(DEFALUT_TEMPLATE_SUFFIX) ? viewName : (viewName + DEFALUT_TEMPLATE_SUFFIX));
        filePath = filePath.replaceAll("/+", "/");

        return new MyView(new File(filePath));
    }
}
