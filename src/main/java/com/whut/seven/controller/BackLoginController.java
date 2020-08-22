package com.whut.seven.controller;

import com.whut.seven.entity.User;
import com.whut.seven.service.BackUserService;
import com.whut.seven.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @Author Zrt
 * @Date 2020/8/21 21:14
 */
@Controller
@RequestMapping("/back")
public class BackLoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private BackUserService backUserService;

    @PostMapping("/login")
    public String login(String username,
                        String password,
                        HttpSession session,
                        Model model) {
        User user = backUserService.findByUsername(username);
        if(user==null){
            model.addAttribute("message","用户名和密码错误");
            // 返回的是页面
            return "/admin/login";
        }
        if(user.getRole().equals(1)){
            model.addAttribute("message","该账号不属于管理员账号");
            // 返回的是页面
            return "/admin/login";
        }
        if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
            session.setAttribute("user", user);
            // 重定向的路径
            return "redirect:/admin-bill.html";
        } else {
            model.addAttribute("message","用户名和密码错误");
            // 返回的是页面
            return "/admin/login";
        }
    }

    @PostMapping("/changePassword")
    public String changePassword(String username,
                        String password,
                        HttpSession session,
                        Model model) {
        User user = backUserService.findByUsername(username);
        if(user.getRole().equals(1)){
            model.addAttribute("message","该账号不属于管理员账号");
            // 返回的是页面
            return "/back/login";
        }
        if(!user.getPassword().equals(password)){
            model.addAttribute("message","旧密码不正确");
            return null;
        }else{
            user.setPassword(password);
            model.addAttribute("message","修改成功");
            return null;
        }

    }

}
