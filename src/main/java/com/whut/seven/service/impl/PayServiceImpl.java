package com.whut.seven.service.impl;

import com.whut.seven.dao.ElectricityDao;
import com.whut.seven.dao.PayDao;
import com.whut.seven.dao.PayUnitDao;
import com.whut.seven.entity.Electricity;
import com.whut.seven.entity.User;
import com.whut.seven.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class PayServiceImpl implements PayService {


    @Autowired
    PayDao payDao;

    @Autowired
    PayUnitDao payUnitDao;

    @Autowired
    ElectricityDao electricityDao;


    public Page<Electricity> listPaymentHistory(Pageable pageable, final User user, String startdate, String enddate) {
        return payDao.findAll(new Specification<Electricity>() {
            @Override
            public Predicate toPredicate(Root<Electricity> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();

                /*用户选择*/
                if(user.getUsername() !=null&&!"".equals(user.getUsername()) ) {
                    predicates.add(cb.equal(root.<Boolean>get("user").get("username"),user.getUsername()));
                }

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
                Date startDate = null;
                Date endDate = null;
                try {
                    startDate = format.parse(startdate);
                    endDate = format.parse(enddate);
                } catch(Exception e){
                }

                /*起始日期*/
                if(!startdate.equals("")){
                    predicates.add(cb.greaterThanOrEqualTo(root.<Date>get("createTime"), startDate));
                }

                /*结束日期*/
                /**
                 * 存在bug不知道为什么，如果只有enddate不成立
                 */
                if(!enddate.equals("")) {
                    predicates.add(cb.lessThanOrEqualTo(root.<Date>get("createTime"), endDate));
                }

                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }

    @Override
    public void electricityGetPaid(String id, String username) {
        payDao.electricityGetPaid(id,username);
    }
}
