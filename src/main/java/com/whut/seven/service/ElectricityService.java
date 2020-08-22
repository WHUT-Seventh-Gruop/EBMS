package com.whut.seven.service;

import com.whut.seven.entity.Electricity;
import com.whut.seven.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ElectricityService {

    Page<Electricity> findAllElectricity(User user, Pageable pageable);
}
