package com.whut.seven.controller;

import com.whut.seven.entity.Result;
import com.whut.seven.entity.User;
import com.whut.seven.service.BackUserService;
import com.whut.seven.service.UserService;
import com.whut.seven.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.security.provider.MD5;

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
@Autowired
    HttpSession session;

    @PostMapping("/login")
    public String login(String username,
                        String password,
                        Model model) {
        User user = backUserService.findByUsername(username);
        if (user == null) {
            model.addAttribute("message", "用户名和密码错误");
            // 返回的是页面
            return "/admin/login";
        }
        if (user.getRole().equals(1)) {
            model.addAttribute("message", "该账号不属于管理员账号");
            // 返回的是页面
            return "/admin/login";
        }
        if (user.getUsername().equals(username) && user.getPassword().equals(MD5Util.code(password))) {
            session.setAttribute("user", user);
            // 重定向的路径
            return "redirect:/back/electricity/elec";
        } else {
            model.addAttribute("message", "用户名和密码错误");
            // 返回的是页面
            return "/admin/login";
        }
    }

    @PostMapping("/changePassword")
    @ResponseBody
    public Result changePassword(String oldPassword,
                                 String newPassword,
                                 String reNewPassword,
                                 HttpSession session,
                                 Model model) {
        User user = (User) session.getAttribute("user");
        Result<String> result = new Result();
        if (user == null) {
            model.addAttribute("message", "请先登录！");
            result.setSuccess(false);
            result.setMessage("请先登录！");
        }
        if (!newPassword.equals(reNewPassword)) {
            result.setSuccess(false);
            result.setMessage("两次输入的新密码不一致！");
        }
        if (user.getPassword().equals(MD5Util.code(oldPassword))) {
            user.setPassword(MD5Util.code(newPassword));
            backUserService.saveAdmin(user);
            result.setSuccess(true);
            result.setMessage("修改成功！");
        }else{
            result.setSuccess(false);
            result.setMessage("旧密码输入错误！");
        }
        return result;
    }

    /**
     * 退出系统
     * @param session
     * @return
     */
    @RequestMapping("/loginOut")
    public String loginOut(HttpSession session){
        session.removeAttribute("user");
        return "/admin/login";
    }


}
