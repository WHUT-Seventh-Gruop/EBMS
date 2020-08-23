package com.whut.seven.controller;


import com.whut.seven.service.PayUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/payUnit")
public class PayUnitController {

    @Autowired
    PayUnitService payUnitService;

    @InitBinder
    public void InitBinder(WebDataBinder binder){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping("/findAllCampus")
    @ResponseBody
    public List<String> findAllCampus(Model model){

        List<String> list = payUnitService.findAllCampus();

        System.out.println(list);

        return list;

    }

    @RequestMapping("/findAllBuildingsByCampus")
    @ResponseBody
    public List<String> findAllBuildingsByCampus(String campus){

        List<String> list = payUnitService.findAllBuildingsByCampus(campus);
        System.out.println(list);
        return list;

    }

    @RequestMapping("/findAllDormitoriesByBuilding")
    @ResponseBody
    public List<String> findAllDormitoriesByBuilding(String building){

        List<String> list = payUnitService.findAllDormitoriesByBuilding(building);
        System.out.println(list);
        return list;

    }




}
