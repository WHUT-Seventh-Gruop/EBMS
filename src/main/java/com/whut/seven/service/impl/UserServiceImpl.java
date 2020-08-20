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
     * @param password 密码
     * @param role     身份
     * @return
     */
    @Override
    public User login(String username, String password, Integer role) {
        return this.userDao.findUserByUsernameAndPasswordAndRole(username, password, role);

    }
}
