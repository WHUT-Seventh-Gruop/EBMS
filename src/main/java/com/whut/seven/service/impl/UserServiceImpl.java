package com.whut.seven.service.impl;

import com.whut.seven.dao.UserDao;
import com.whut.seven.entity.User;
import com.whut.seven.service.UserService;
import com.whut.seven.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author Zrt
 * @Date 2020/8/20 22:02
 */
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    /**
     * 用户登录
     *
     * @param username 账号
     * @param password 密码(使用MD5加密
     * @param role     身份
     * @return 登录的用户信息
     */
    @Override
    public User login(String username, String password, Integer role) {
        return this.userDao.findUserByUsernameAndPasswordAndRole(username, MD5Util.code(password), role);

    }

    /**
     * 注册用户
     *
     * @param username 账号
     * @param password 密码（使用MD5加密
     * @param role     身份
     * @return 注册的用户信息
     */
    @Override
    public User register(String username, String password, Integer role) {
        User user = new User(username, password, role);
        return this.userDao.save(user);
    }

    /**
     * 注册用户
     *
     * @param user 注册的用户信息
     * @return 返回注册的信息
     */
    @Override
    public User register(User user) {
        return this.userDao.save(user);
    }
}
