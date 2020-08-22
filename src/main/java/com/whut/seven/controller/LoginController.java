package com.whut.seven.controller;

import com.whut.seven.entity.User;
import com.whut.seven.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     */
    @PostMapping("/login1")
    public String login(String username,
                        String password
            , HttpSession session,
                        Model model){
        User user = this.userService.login(username, password);
        if(user!=null){
            session.setAttribute("user",user);
            System.out.println("登录成功！");
            return "redirect:/index.html";
        }else{
            model.addAttribute("message","用户名和密码错误！");
            return "/login.html";
        }
    }

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public String register(User user, String repassword, RedirectAttributes attributes){
        user.setRole(1);
            if(user.getPassword().equals(repassword)){
                this.userService.register(user);
                attributes.addFlashAttribute("message","注册成功！");
                //System.out.println("注册成功!");
                //返回登录
            }else{
                attributes.addFlashAttribute("message","两次密码不一致,注册失败！");
                //System.out.println("两次密码不一致!");
                //重定向注册
            }
        return "redirect:/signupsuccess.html";
        }


}
