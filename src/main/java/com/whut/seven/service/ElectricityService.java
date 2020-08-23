package com.whut.seven.service;

import com.whut.seven.entity.Electricity;
import com.whut.seven.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface ElectricityService {

    Page<Electricity> findAllElectricityByIsPay(User user, Pageable pageable, boolean isPay);
    Page<Electricity> findAllElectricityByTimeAndIsPay(User user, Pageable pageable, Date date1, Date date2 , boolean isPay);
}
