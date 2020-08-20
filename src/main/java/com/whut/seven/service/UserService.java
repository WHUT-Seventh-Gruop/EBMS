package com.whut.seven.service;


import com.whut.seven.entity.User;

/**
 * @Author Zrt
 * @Date 2020/8/19 15:27
 */
public interface UserService {
    /**
     * 用户登录
     *
     * @param username 账号
     * @param password 密码
     * @param role     身份
     * @return
     */
    User login(String username, String password, Integer role);
}
