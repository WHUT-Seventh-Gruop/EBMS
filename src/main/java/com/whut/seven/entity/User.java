package com.whut.seven.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户表
 *
 * @Author Zrt
 * @Date 2020/8/20 21:28
 */
@Entity
@Table(name = "t_user")
public class User {
    /**
     * 账号用户名
     */
    @Id
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户类别（身份）
     * 1 普通用户
     * 2 管理员
     * 3 超级账号
     */
    private Integer role;
    /**
     * 该用户的所有账单
     */
    @OneToMany(mappedBy = "user")
    private List<Electricity> electricityList = new ArrayList<>();

    public List<Electricity> getElectricityList() {
        return electricityList;
    }

    public void setElectricityList(List<Electricity> electricityList) {
        this.electricityList = electricityList;
    }

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
