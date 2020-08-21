package com.whut.seven.service.impl;

import com.whut.seven.dao.UserDao;
import com.whut.seven.entity.User;
import com.whut.seven.service.BackUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author Zrt
 * @Date 2020/8/21 21:00
 */
public class BackUserServiceImpl implements BackUserService {
    @Autowired
    private UserDao userDao;
    /**
     * 修改自己的密码
     *
     * @param username 用户名
     * @param password 自己的密码
     * @return 修改后的信息
     */
    @Override
    public User changeSelfPassword(String username, String password) {
        User userByUsername = userDao.findUserByUsername(username);
        userByUsername.setPassword(password);
        return userDao.save(userByUsername);
    }

    /**
     * 超级管理员 添加管理员
     *
     * @param username 用户名
     * @param password 密码
     * @param role     身份
     * @return 说添加的信息
     */
    @Override
    public User addAdmin(String username, String password, Integer role) {
        User user = new User(username,password,role);
        return userDao.save(user);
    }

    /**
     * 超级管理员查询所有的管理员
     *
     * @return 所有的管理员信息
     */
    @Override
    public List<User> findAllAdmin() {
        return userDao.findAll();
    }

    /**
     * 保存管理员
     *
     * @param user 管理员信息
     * @return 被保存的管理员信息
     */
    @Override
    public User saveAdmin(User user) {
        return userDao.save(user);
    }

    /**
     * 根据用户名删除用户信息
     *
     * @param username 用户名
     * @param role     用户的类别
     * @return 被删除的信息
     */
    @Override
    public User deleteAdminByUsername(String username, Integer role) {
        return userDao.deleteByUsername(username);
    }
}
