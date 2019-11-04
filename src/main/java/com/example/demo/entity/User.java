package com.example.demo.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author liufei
 * @since 2019-10-31 17:52
 */
public class User implements Serializable {

    private static final long serialVersionUID = -1369148652554782331L;

    @NotEmpty(message = "用户名不可为空")
    private String username;
    @NotNull(message = "年龄不可为空")
    private Integer age;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}
