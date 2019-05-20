package com.gupaoedu.concurrent.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author laihui
 * @Date 2019/5/20
 * @Desc
 * @Version 1.0
 **/
public class RequestDto {
    private Map<String, Object> header;// 请求报文头

    private List<RequestElement> elements;// 请求节点

    public RequestDto(Map<String, Object> header, List<RequestElement> elements) {
        this.header = header;
        this.elements = elements;
    }

    public Map<String, Object> getHeader() {
        return header;
    }

    public void setHeader(Map<String, Object> header) {
        this.header = header;
    }

    public List<RequestElement> getElements() {
        return elements;
    }

    public void setElements(List<RequestElement> elements) {
        this.elements = elements;
    }

    @Override
    public String toString() {
        return "{header:" + header + ", elements: " + elements + "}";
    }
}
