package com.gupao.dubbo.rpc.service.api;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @Author laihui
 * @Date 2019/7/26
 * @Desc
 * @Version 1.0
 **/
@XmlRootElement
public class User implements Serializable {
    private String name;
    private Integer id;
    private String addr;

    public User(){}

    public User(String name) {
        this(name, 0, "shanghai");
    }

    public User(String name, int id, String addr) {
        this.name = name;
        this.id = id;
        this.addr = addr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
