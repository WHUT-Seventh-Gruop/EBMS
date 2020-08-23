package com.whut.seven.controller;

import com.whut.seven.entity.Electricity;
import com.whut.seven.entity.User;
import com.whut.seven.service.BackElectricityService;
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

/**
 * @Author Zrt
 * @Date 2020/8/23 23:14
 */
@Controller
@RequestMapping("/back/electricity")
public class BackElectricityController {
    @Autowired
    BackElectricityService backElectricityService;

    @GetMapping("/all")
    public String admins(@PageableDefault(size = 5, direction = Sort.Direction.DESC) Pageable pageable, String searchText, Integer index,
                         Model model) {
        Page<Electricity> allElectricity;
        if (index !=null && index == 1) {
            allElectricity = backElectricityService.findAllElectricity(pageable, null, new Long(searchText));
        } else {
            allElectricity = backElectricityService.findAllElectricity(pageable, searchText, null);
        }
        model.addAttribute("page", allElectricity);
        model.addAttribute("searchText", searchText);
        model.addAttribute("index", index);

        return "/admin/bill";
    }

    @PostMapping("/search")
    public String search(@PageableDefault(size = 5, direction = Sort.Direction.DESC) Pageable pageable, String searchText, Integer index,
                         Model model) {
        Page<Electricity> allElectricity;
        if (index == 1) {
            allElectricity = backElectricityService.findAllElectricity(pageable, null, new Long(searchText));
        } else {
            allElectricity = backElectricityService.findAllElectricity(pageable, searchText, null);
        }
        model.addAttribute("page", allElectricity);
        model.addAttribute("searchText", searchText);
        model.addAttribute("index", index);

        return "/admin/bill :: electricityList";
    }


}
