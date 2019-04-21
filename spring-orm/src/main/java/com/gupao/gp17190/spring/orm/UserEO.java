package com.gupao.gp17190.spring.orm;

import javax.persistence.Table;
import java.util.Date;

/**
 * @author laihui
 * @version 1.0
 * @date 2019/4/21
 * @description
 **/
@Table(name="t_user")
public class UserEO {
    private Integer id;

    private String name;

    private Integer age;

    private Long createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "UserEO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", createDate=" + createDate +
                '}';
    }
}
