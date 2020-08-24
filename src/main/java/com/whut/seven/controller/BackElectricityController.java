package com.whut.seven.controller;

import com.whut.seven.entity.Electricity;
import com.whut.seven.entity.PayUnit;
import com.whut.seven.entity.User;
import com.whut.seven.service.BackElectricityService;
import com.whut.seven.service.BackUnitService;
import com.whut.seven.service.BackUserService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @Author Zrt
 * @Date 2020/8/23 23:14
 */
@Controller
@RequestMapping("/back/electricity")
public class BackElectricityController {
    @Autowired
    private BackElectricityService backElectricityService;

    @Autowired
    private BackUserService backUserService;

    @Autowired
    BackUnitService backUnitService;
    @Autowired
    HttpSession session;

    @GetMapping("/all")
    public String admins(@PageableDefault(size = 5, direction = Sort.Direction.ASC) Pageable pageable, String searchText, Integer index,
                         Model model) {
        Page<Electricity> allElectricity;
        if (index !=null && index == 1) {
            allElectricity = backElectricityService.findAllElectricity(pageable, null,searchText);
        } else {
            allElectricity = backElectricityService.findAllElectricity(pageable, searchText, null);
        }
        model.addAttribute("page", allElectricity);
        model.addAttribute("searchText", searchText);
        model.addAttribute("index", index);

        return "/admin/bill";
    }

    @PostMapping("/search")
    public String search(@PageableDefault(size = 5, direction = Sort.Direction.ASC) Pageable pageable, String searchText, Integer index,
                         Model model) {
        Page<Electricity> allElectricity;
        if (index == 1) {
            allElectricity = backElectricityService.findAllElectricity(pageable, null, searchText);
        } else {
            allElectricity = backElectricityService.findAllElectricity(pageable, searchText, null);
        }
        model.addAttribute("page", allElectricity);
        model.addAttribute("searchText", searchText);
        model.addAttribute("index", index);

        return "/admin/bill :: electricityList";
    }

    @PostMapping("/add")
    public String addElectricity(String unitId, String userId, String date,String payTime, String electricityConsumption, String electricCharge,Model model)  {
        Electricity electricity = new Electricity();
        PayUnit unitById = backUnitService.findUnitById(unitId);
        if(unitById==null){
            model.addAttribute("message","支付单位不存在!");
            return "/admin/bill-add";
        }
        User byUsername = backUserService.findByUsername(userId);
        if(byUsername==null){
            electricity.setUser(null);
        }else{
            electricity.setUser(byUsername);
        }
        if(electricCharge==null){
            model.addAttribute("message","用电量为空!");
            return "/admin/bill-add";
        }
        if(electricityConsumption==null){
            model.addAttribute("message","电费为空!");
            return "/admin/bill-add";
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        if(date != null && !"".equals(date)) {
            Date createTime = null;
            try {
                createTime = dateFormat.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
                model.addAttribute("message","起止时间日期格式错误!");
                return "/admin/bill-add";
            }
            electricity.setCreateTime(createTime);
        }else{
            model.addAttribute("message","起止时间为空!");
            return "/admin/bill-add";
        }
        DateFormat payTimeFormat = new SimpleDateFormat("yyyy-MM-dd");
        if(payTime!=null && !"".equals(payTime)){
            Date payTimeDate = null;
            try {
                payTimeDate = payTimeFormat.parse(payTime);
            } catch (ParseException e) {
                model.addAttribute("message","支付时间日期格式错误!");
                return "/admin/bill-add";
            }
            electricity.setPayTime(payTimeDate);
            electricity.setPay(true);
        }else{
            electricity.setPayTime(null);
            electricity.setPay(false);
        }
        electricity.setId(UUID.randomUUID().toString());
        electricity.setPayUnit(unitById);
        electricity.setElectricityConsumption(new BigDecimal(electricityConsumption));
        electricity.setElectricCharge(new BigDecimal(electricCharge));
        backElectricityService.addElectricity(electricity);
        model.addAttribute("message","添加成功!");
        return "/admin/bill-add";
    }

    /**
     * 根据账单ID删除账单信息
     * @param id 账单ID
     * @return 被删除的信息
     */
    @GetMapping("/{id}/delete")
    public String deleteAdminByUsername(@PathVariable("id") String id, RedirectAttributes attributes){
        backElectricityService.deleteElectricityById(id);
        return "redirect:/back/electricity/all";
    }

    @GetMapping("/{id}/edit")
    public String editInput(@PathVariable("id") String id, Model model){
        Electricity byId = backElectricityService.findById(id);
        System.out.println(byId);
        model.addAttribute("electricity",byId);
        return "/admin/bill-upd";
    }
    @PostMapping("/update")
    public String update(String billId,String unitId, String userId, String date,String payTime, String electricityConsumption, String electricCharge,Model model)  {
        Electricity electricity = backElectricityService.findById(billId);
        User byUsername = backUserService.findByUsername(userId);
        if(byUsername==null){
            model.addAttribute("message","用户ID不存在!");
            return "/admin/bill-add";
        }
        PayUnit unitById = backUnitService.findUnitById(unitId);
        if(unitById==null){
            model.addAttribute("message","支付单位不存在!");
            return "/admin/bill-add";
        }
        if(electricCharge==null){
            model.addAttribute("message","用电量为空!");
            return "/admin/bill-add";
        }
        if(electricityConsumption==null){
            model.addAttribute("message","电费为空!");
            return "/admin/bill-add";
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        if(date != null && !"".equals(date)) {
            Date createTime = null;
            try {
                createTime = dateFormat.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
                model.addAttribute("message","起止时间日期格式错误!");
                return "/admin/bill-add";
            }
            electricity.setCreateTime(createTime);
        }else{
            model.addAttribute("message","起止时间为空!");
            return "/admin/bill-add";
        }
        DateFormat payTimeFormat = new SimpleDateFormat("yyyy-MM-dd");
        if(payTime!=null && !"".equals(payTime)){
            Date payTimeDate = null;
            try {
                payTimeDate = payTimeFormat.parse(payTime);
            } catch (ParseException e) {
                model.addAttribute("message","支付时间日期格式错误!");
                return "/admin/bill-add";
            }
            electricity.setPayTime(payTimeDate);
            electricity.setPay(true);
        }else{
            electricity.setPayTime(null);
            electricity.setPay(false);
        }
        electricity.setPayUnit(unitById);
        electricity.setUser(byUsername);
        electricity.setElectricityConsumption(new BigDecimal(electricityConsumption));
        electricity.setElectricCharge(new BigDecimal(electricCharge));
        backElectricityService.updateElectricity(electricity);
        model.addAttribute("electricity",electricity);

        model.addAttribute("message","修改成功!");
        return "/admin/bill-upd";
    }
    @GetMapping("/sum")
    @ResponseBody
    public String calSumElectricityConsumptionThisMonth(Model model){
        Double aDouble = backElectricityService.calSumElectricityConsumptionThisMonth();
        model.addAttribute("sum",aDouble);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sum",aDouble);
        return jsonObject.toString();
    }

    @GetMapping("/elec")
    public String statisticData(Model model){
        Double electricityConsumptionSum = backElectricityService.calSumElectricityConsumptionThisMonth();
        model.addAttribute("electricityConsumptionSum",electricityConsumptionSum);
        Long unitCount = backUnitService.countUnit();
        model.addAttribute("unitCount",unitCount);
        Double sumElectricityChargeThisMonth = backElectricityService.calSumElectricityChargeThisMonth();
        model.addAttribute("sumElectricityChargeThisMonth",sumElectricityChargeThisMonth);
        Map<Long, Double> electricityConsumptionGroupByUnit = backElectricityService.findElectricityConsumptionGroupByUnit();
        System.out.println(electricityConsumptionGroupByUnit);
        return "/admin/elec";
    }

    @RequestMapping("/tableData")
    @ResponseBody
    public Map<Long, Double> tableData(){
        JSONObject jsonObject = new JSONObject();
        Map<Long, Double> electricityConsumptionGroupByUnit = backElectricityService.findElectricityConsumptionGroupByUnit();
        jsonObject.put("electricityConsumptionGroupByUnit",electricityConsumptionGroupByUnit);
        return electricityConsumptionGroupByUnit;
    }

}
