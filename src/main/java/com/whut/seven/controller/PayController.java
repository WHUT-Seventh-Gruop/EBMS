package com.whut.seven.controller;

import com.whut.seven.entity.User;
import com.whut.seven.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.util.Date;

@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private PayService payService;

    /*
    * parameter：user
    * operation:list all payment historys of this user
    *
    * */
    @GetMapping("/payhistorys")
    public String payhistorys(@PageableDefault(size = 5,sort = {"payTime"},direction = Sort.Direction.DESC) Pageable pageable,
                        HttpSession session,
                        Model model){
        model.addAttribute("page",this.payService.listPaymentHistory(pageable,(User)session.getAttribute("user"), "", ""));
        return "/payhistory";
    }

    @PostMapping("/payhistorys/search")
    public String search(@PageableDefault(size = 5,sort = {"payTime"},direction = Sort.Direction.DESC) Pageable pageable,
                         HttpSession session, String startdate ,String enddate,
                         Model model){
        System.out.println("select payments' history from "+startdate+" to "+enddate);

        model.addAttribute("page",this.payService.listPaymentHistory(pageable,(User)session.getAttribute("user"),startdate,enddate));
        return "/payhistory:: payhistoryList";
    }

    @GetMapping("/electricityGetPaid")
    public String electricityGetPaid(String id,HttpSession session)
    {
        User user = (User)session.getAttribute("user");
        Date date = new Date();
        System.out.println(date);

        payService.electricityGetPaid(id,user.getUsername(),date);
        return "/paymentplatform.html";
    }
}
