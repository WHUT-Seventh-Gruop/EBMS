package com.whut.seven.controller;

import com.whut.seven.entity.Electricity;
import com.whut.seven.entity.PayUnit;
import com.whut.seven.entity.User;
import com.whut.seven.service.ElectricityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/electricity")
public class ElectricityController {

    @Autowired
    ElectricityService electricityService;


    @InitBinder
    public void InitBinder(WebDataBinder binder){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping("findById")
    public String findById(String id,Model model)
    {
        model.addAttribute("electricity",electricityService.findById(id));
        return "/pay.html";
    }


    @GetMapping("/findAll")
    public String findAll(@PageableDefault(size = 8, sort = "payTime", direction = Sort.Direction.DESC) Pageable pageable,
                          Model model,
                          HttpSession session
    ){



        Page<Electricity> allElectricity = this.electricityService.findAllElectricityByIsPay((User) session.getAttribute("user"),pageable,true);

        model.addAttribute("allElectricity",allElectricity);

        return "/payhistory.html";



    }

    @GetMapping("/findAllByTime")
    public String findAllByTime(Date date1, Date date2,
                                @PageableDefault(size = 8, sort = "payTime", direction = Sort.Direction.DESC) Pageable pageable,
                                Model model,
                                HttpSession session
    ) throws ParseException {


        String startStr = "1970.1.1 ";
        String endStr = "2050.12.31 ";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");

        if(date1==null)
            date1 = sdf.parse(startStr);
        if(date2==null)
            date2 = sdf.parse(endStr);


        System.out.println(date1);
        Page<Electricity> allElectricity = this.electricityService.findAllElectricityByTimeAndIsPay((User) session.getAttribute("user"),pageable,date1,date2,true);

        model.addAttribute("allElectricity",allElectricity);

        return "/payhistory.html";



    }

    @GetMapping("/findAllPaying")
    public String findAllPaying(PayUnit payUnit,String date,
                                 @PageableDefault(size = 8, sort = "createTime", direction = Sort.Direction.DESC) Pageable pageable,
                                 Model model,
                                 HttpSession session
    ) throws ParseException {


        StringBuffer date1String = new StringBuffer();
        StringBuffer date2String = new StringBuffer();

        if(date.isEmpty())
        {

            date1String.append("1970-01-01");
            date2String.append("2050-12-31");

        }else{

            date1String.append(date);
            int year = Integer.parseInt(date1String.substring(0,3));
            String month = date1String.substring(date1String.lastIndexOf("-")+1);
            date1String.append("-01");


            date2String.append(date);


            System.out.println("月份："+month);

            if(month.equals("01") || month.equals("03") || month.equals("05") || month.equals("07") || month.equals("08") || month.equals("10") || month.equals("12") )
                date2String.append("-31");
            else if(month.equals("02") && year%4==0 )
                date2String.append("-29");
            else if(month.equals("02") && year%4!=0)
                date2String.append("-28");
            else
                date2String.append("-30");


        }


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date1 = sdf.parse(date1String.toString());
        Date date2 = sdf.parse(date2String.toString());




        Page<Electricity> allElectricity = this.electricityService.findAllElectricity(pageable,payUnit,date1,date2);

        model.addAttribute("allElectricity",allElectricity);

        return "billsearch1.html";


    }
}
