package com.whut.seven.service;

import com.whut.seven.entity.Electricity;
import com.whut.seven.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface PayService {
    public Page<Electricity> listPaymentHistory(Pageable pageable, final User user, String startdate, String enddate);

    void electricityGetPaid(String id, String username, Date date);
}
