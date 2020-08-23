package com.whut.seven.controller;

import com.whut.seven.entity.User;
import com.whut.seven.service.BackUserService;
import com.whut.seven.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author Zrt
 * @Date 2020/8/21 21:11
 */
@Controller
@RequestMapping("/admin")
public class BackAdminController {
    @Autowired
    private BackUserService backUserService;
    @Autowired
    HttpSession session;
    /**
     * 超级管理员 添加管理员
     * @param username 用户名
     * @param password 密码
     * @param rePassword 确认密码
     * @return 说添加的信息
     */
    @PostMapping("/addAdmin")
    public String addAdmin(String username, String password,String rePassword, Model model){
        User user = (User)session.getAttribute("user");
        if(user.getRole().equals(1)){
            model.addAttribute("message","只有超级用户可以管理管理员!");
        }else{
            if(username==null || password==null || rePassword==null || "".equals(username) || "".equals(password) || "".equals(rePassword)){
                model.addAttribute("message","不能存在空字段!");
            }else{
                User byUsername = backUserService.findByUsername(username);
                if(!password.equals(rePassword)){
                    model.addAttribute("message","两次填写的密码不一致!");
                }else if(byUsername!=null){
                    model.addAttribute("message","该用户名已存在!");
                }else{
                    backUserService.addAdmin(username, MD5Util.code(password), 2);
                    model.addAttribute("message","添加成功!");
                }
            }
        }
        return "/admin/users-add";
    }

    @GetMapping("/admins")
    public String admins(@PageableDefault(size = 5,direction = Sort.Direction.DESC) Pageable pageable,String username,
                        Model model){
        Page<User> allAdmin = backUserService.findAllAdmin(pageable,username);
        model.addAttribute("page",allAdmin);
        model.addAttribute("searchUsername",username);
        return "/admin/users";
    }

    @PostMapping("/admins/search")
    public String search(@PageableDefault(size = 5,direction = Sort.Direction.DESC) Pageable pageable,String username,
                         Model model){
        Page<User> allAdmin = backUserService.findAllAdmin(pageable,username);
        model.addAttribute("page",allAdmin);
        model.addAttribute("searchUsername",username);
        return "/admin/users :: adminList";
    }

    /**
     * 超级管理员查询所有的管理员
     * @return 所有的管理员信息
     */
    @GetMapping("/findAll")
    List<User> findAllAdmin(RedirectAttributes attributes){
        User user = (User)session.getAttribute("user");
        if(user.getRole().equals(1)){
            attributes.addFlashAttribute("message","只有超级用户可以管理管理员!");
            return null;
        }else {
         //   return backUserService.findAllAdmin();
            return null;
        }
    }

    /**
     * 更细管理员
     * @param username 用户名
     * @param password 密码
     * @param rePassword 确认密码
     * @return 被保存的管理员信息
     */
    @PostMapping("/update")
    public String saveAdmin(String username, String password,String rePassword, Model model){
        User user1 = (User)session.getAttribute("user");
        User byUsername = backUserService.findByUsername(username);
        model.addAttribute("user",byUsername);
        if(user1.getRole().equals(1)){
            model.addAttribute("message","只有超级用户可以管理管理员!");
            return null;
        }else {
            if(username==null || password==null || rePassword==null || "".equals(username) || "".equals(password) || "".equals(rePassword)){
                model.addAttribute("message","不能存在空字段!");
            }else{
                if(!password.equals(rePassword)){
                    model.addAttribute("message","两次填写的密码不一致!");
                }else{
                    backUserService.addAdmin(username, MD5Util.code(password), 2);
                    model.addAttribute("message","修改成功!");
                }
            }
            return "/admin/users-upd";
        }
    }

    /**
     * 根据用户名删除用户信息
     * @param username 用户名
     * @return 被删除的信息
     */
    @GetMapping("/{username}/delete")
    public String deleteAdminByUsername(@PathVariable("username") String username, RedirectAttributes attributes){
        User user = (User)session.getAttribute("user");
        if(user.getRole().equals(1)){
            attributes.addFlashAttribute("message","只有超级用户可以管理管理员!");
        }else {
             backUserService.deleteAdminByUsername(username);
        }
        return "redirect:/admin/admins";
    }

    @GetMapping("/{username}/edit")
    public String editInput(@PathVariable("username") String username, Model model){
        User byUsername = backUserService.findByUsername(username);
        model.addAttribute("user",byUsername);
        return "/admin/users-upd";
    }

}
