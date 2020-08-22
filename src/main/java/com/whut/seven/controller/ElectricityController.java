package com.whut.seven.controller;

import com.whut.seven.entity.Electricity;
import com.whut.seven.entity.User;
import com.whut.seven.service.ElectricityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/electricity")
public class ElectricityController {

    @Autowired
    ElectricityService electricityService;

    @GetMapping("/findAll")
    public String findAll(@PageableDefault(size = 5, sort = "payTime", direction = Sort.Direction.DESC) Pageable pageable,
                          Model model,
                          HttpSession session
                          ){



        Page<Electricity> allElectricity = this.electricityService.findAllElectricity((User) session.getAttribute("user"),pageable);

        model.addAttribute("allElectricity",allElectricity);

        return "/payhistory.html";



    }
}
