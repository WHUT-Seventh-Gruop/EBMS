package com.whut.seven.controller;

import com.whut.seven.entity.PayUnit;
import com.whut.seven.service.BackPayUnitService;
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

@Controller
@RequestMapping("/admin")
public class BackPayUnitController {

    @Autowired
    HttpSession session;
    @Autowired
    private BackPayUnitService backPayUnitService;

    @GetMapping("/PayUnit")
    public String PayUnits(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable,
                           PayUnit payUnit,
                           Model model) {

        model.addAttribute("campus", backPayUnitService.listCampus());
        model.addAttribute("building_no", backPayUnitService.listBuildingNo());
        model.addAttribute("page", backPayUnitService.findAllPayUnit(pageable));
        return "/admin/unit";
    }

    @PostMapping("/PayUnit/search")
    public String search(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable,
                         PayUnit payUnit,
                         Model model) {

        Page<PayUnit> payUnits = backPayUnitService.listPayUnit(pageable, payUnit);
        model.addAttribute("page", payUnits);
        return "/admin/unit :: payUnitList";
    }

    /*
     增加单位信息部分代码
     */
    @RequestMapping("/PayUnit/showAddUnit")
    public String showAddUnit(Model model) {
        model.addAttribute("PayUnit", new PayUnit());
        model.addAttribute("campus", backPayUnitService.listCampus());
        model.addAttribute("building_no", backPayUnitService.listBuildingNo());
        return "/admin/unit-add";
    }

    @PostMapping("/PayUnit/savePayUnit")
    public String savePayUnit(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable,
                              PayUnit payUnit, RedirectAttributes ras, Model model) {
        if (payUnit.getId() == null) {
            model.addAttribute("message", "增加信息ID为空，请重试!");
        } else {
            PayUnit t = this.backPayUnitService.addPayUnit(payUnit);
            model.addAttribute("message", "更新操作成功!");
        }


        model.addAttribute("campus", backPayUnitService.listCampus());
        model.addAttribute("building_no", backPayUnitService.listBuildingNo());
        model.addAttribute("page", backPayUnitService.findAllPayUnit(pageable));
        return "/admin/unit";
    }


    /*
更新模块
     */

    @RequestMapping("/PayUnit/showUpdUnit")
    public String showUpdUnit(Model model, Long id) {
        model.addAttribute("campus", backPayUnitService.listCampus());
        model.addAttribute("building_no", backPayUnitService.listBuildingNo());
        PayUnit payUnit = this.backPayUnitService.getPayUnitByID(id);
        model.addAttribute("payUnit", payUnit);
        return "/admin/unit-upd";
    }

    @RequestMapping("/PayUnit/updateUnit")
    public String updateUnit(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable,
                             PayUnit payUnit, RedirectAttributes attributes, Model model) {
        PayUnit updUnit = this.backPayUnitService.updatePayUnit(payUnit);

        if (updUnit == null) {
            model.addAttribute("message", "更新操作失败，请重试!");

        } else {
            model.addAttribute("message", "更新操作成功!");
        }


        model.addAttribute("campus", backPayUnitService.listCampus());
        model.addAttribute("building_no", backPayUnitService.listBuildingNo());
        model.addAttribute("page", backPayUnitService.findAllPayUnit(pageable));
        return "/admin/unit";
    }
    /*
删除模块
     */
    
    @RequestMapping("/PayUnit/deleteUnitById")
    public String deletePayUnitById(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable,
                                    Long id, RedirectAttributes attributes, Model model, PayUnit payUnit) {
        if (id != null) {
            try {
                backPayUnitService.deletePayUnitById(id);
                model.addAttribute("message", "删除操作成功！");
            } catch (Exception e) {
                model.addAttribute("message", "删除操作失败，该条信息有用户使用！");
            }
        } else {
            model.addAttribute("message", "删除操作失败，请重试!");
        }
        model.addAttribute("campus", backPayUnitService.listCampus());
        model.addAttribute("building_no", backPayUnitService.listBuildingNo());
        model.addAttribute("page", backPayUnitService.listPayUnit(pageable, new PayUnit()));
        return "/admin/unit";
    }

}
