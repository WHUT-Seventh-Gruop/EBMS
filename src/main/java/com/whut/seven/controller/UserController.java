package com.whut.seven.controller;

import com.whut.seven.entity.Result;
import com.whut.seven.entity.User;
import com.whut.seven.service.UserService;
import com.whut.seven.util.MD5Util;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 修改自己密码
     */
    /*@PostMapping("/updPassword")
    public String updPassword(String username,
                              HttpSession session,
                              String oldpassword,
                              String newpassword,
                              String repassword,
                              Model model){
        User user=this.userService.findByName(username);
          if(user.getPassword().equals(oldpassword)){
              if(oldpassword!=newpassword){
                  if(newpassword.equals(repassword)){
                      user.setPassword(newpassword);
                      model.addAttribute("message","修改密码成功！");
                      System.out.println("修改密码成功！");
                      return "redirect:/login";
                  }else{
                      model.addAttribute("message","两次新密码不一致！");
                      System.out.println("两次新密码不一致！");
                      return "/user/updPassword";
                  }
              }else{
                  model.addAttribute("message","旧密码和新密码不能一样！");
                  System.out.println("旧密码和新密码一样！");
                  return "/user/updPassword";
              }
          }else{
              model.addAttribute("message","旧密码错误！");
              return "/user/updPassword";
          }


    }*/

    @PostMapping("/updPassword")
    @ResponseBody
    public Result updPassword(HttpSession session,
                              String oldpassword,
                              String newpassword,
                              String repassword,
                              Model model) {
        User user=(User)session.getAttribute("user");
        Result<String> result=new Result();
            if (user.getPassword().equals(MD5Util.code(oldpassword))) {
                if (oldpassword.equals(newpassword)) {
                    model.addAttribute("message", "旧密码和新密码不能一样！");
                    result.setSuccess(false);
                    result.setMessage("旧密码和新密码不能一样!");
                } else {
                    if (newpassword.equals(repassword)) {
                        user.setPassword(MD5Util.code(newpassword));
                        this.userService.changePassword(user);
                        model.addAttribute("message", "修改密码成功！");
                        result.setSuccess(true);
                        result.setMessage("密码修改成功！");
                    } else {
                        model.addAttribute("message", "两次新密码不一致！");

                        result.setSuccess(false);
                        result.setMessage("两次新密码不一致！");
                    }
                }
            } else {
                model.addAttribute("message", "旧密码错误！");
                result.setSuccess(false);
                result.setMessage("旧密码错误！");
            }
        return result;
    }




    @RequestMapping("/checkName")
    @ResponseBody
    public String  checkName(String username){
        JSONObject jsonObject=new JSONObject();
        User user=this.userService.findByName(username);
        if(user!=null){
            jsonObject.put("result","2");//用户已经存在
        }else{
            jsonObject.put("result","1");//用户不存在
        }

        return jsonObject.toString();
    }


}
