package com.whut.seven.service;

import com.whut.seven.entity.Electricity;
import com.whut.seven.entity.PayUnitQuery;
import com.whut.seven.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface ElectricityService {

    Page<Electricity> findAllElectricityByIsPay(User user, Pageable pageable, boolean isPay);
    Page<Electricity> findAllElectricityByTimeAndIsPay(User user, Pageable pageable, Date date1, Date date2 , boolean isPay);


    Page<Electricity> findAllElectricity(Pageable pageable, PayUnitQuery payUnit, Date date1, Date date2);

    Electricity findById(String id);
}
