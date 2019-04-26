package com.gupao.gp17190.springframework.web.servlet;

import java.util.Map;

/**
 * @author laihui
 * @version 1.0
 * @date 2019/4/24
 * @description
 **/
public class MyModelAndView {

    private String viewName;

    private Map<String, Object> model;

    public MyModelAndView(String viewName, Map<String, Object> model) {
        this.viewName = viewName;
        this.model = model;
    }

    public MyModelAndView(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }

    public Map<String, Object> getModel() {
        return model;
    }
}
