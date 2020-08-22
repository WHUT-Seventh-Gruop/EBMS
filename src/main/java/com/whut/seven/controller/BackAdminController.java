package com.whut.seven.controller;

import com.whut.seven.entity.User;
import com.whut.seven.service.BackUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
     * @param role 身份
     * @return 说添加的信息
     */
    @PostMapping("/addAdmin")
    User addAdmin(String username, String password, Integer role, RedirectAttributes attributes){
        User user = (User)session.getAttribute("user");
        if(user.getRole().equals(1)){
            attributes.addFlashAttribute("message","只有超级用户可以管理管理员!");
        }else{
            backUserService.addAdmin(username, password, role);
            attributes.addFlashAttribute("message","添加成功!");
        }
        return null;
    }

    @GetMapping("/admins")
    public String admins(@PageableDefault(size = 5,direction = Sort.Direction.DESC) Pageable pageable,

                        Model model){
        Page<User> allAdmin = backUserService.findAllAdmin(pageable);
        model.addAttribute("page",backUserService.findAllAdmin(pageable));
        return "/admin/users";
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
     * 保存管理员
     * @param user 管理员信息
     * @return 被保存的管理员信息
     */
    @PostMapping("/saveAdmin")
    User saveAdmin(User user, RedirectAttributes attributes){
        User user1 = (User)session.getAttribute("user");
        if(user1.getRole().equals(1)){
            attributes.addFlashAttribute("message","只有超级用户可以管理管理员!");
            return null;
        }else {
            return backUserService.saveAdmin(user);
        }

    }

    /**
     * 根据用户名删除用户信息
     * @param username 用户名
     * @param role 用户的类别
     * @return 被删除的信息
     */
    @PostMapping("/delAdminByUsername")
    User deleteAdminByUsername(String username,Integer role, RedirectAttributes attributes){
        User user = (User)session.getAttribute("user");
        if(user.getRole().equals(1)){
            attributes.addFlashAttribute("message","只有超级用户可以管理管理员!");
            return null;
        }else {
            return backUserService.deleteAdminByUsername(username, role);
        }
    }
}
