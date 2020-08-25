package com.whut.seven.test;
import com.whut.seven.EmsApplication;
import com.whut.seven.dao.ElectricityDao;
import com.whut.seven.dao.PayUnitDao;
import com.whut.seven.entity.Electricity;
import com.whut.seven.entity.PayUnit;
import com.whut.seven.service.BackElectricityService;
import com.whut.seven.service.BackPayUnitService;
import com.whut.seven.service.impl.PayUnitServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;



public class CreateBills {
    @Autowired
    public ElectricityDao electricityDao;

    @Autowired
    public PayUnitDao payUnitDao;

/**
 *
 * 输入的为对应月份 类似2020-08
 *
 * */
    public void CreateManyBills(String month) throws ParseException {
        List<PayUnit> payunits=this.payUnitDao.findAll();
        for(int i=0;i<payunits.size();i++){

            Electricity electricity=new Electricity();

            //id
            electricity.setId(UUID.randomUUID().toString());

            //payunit_id
            PayUnit payUnit=payunits.get(i);
            electricity.setPayUnit(payUnit);

            //charge and consumption number
            electricity.setElectricityConsumption(new BigDecimal(100));
            electricity.setElectricCharge(new BigDecimal(100));

            //date
            String date=month+"-01";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date temp_date = sdf.parse(date.toString());
            electricity.setCreateTime(temp_date);

            this.electricityDao.save(electricity);
        }
    }

    public static void main(String[] args) {
        CreateBills createBills=new CreateBills();
        try {
            createBills.CreateManyBills("2020-03");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
