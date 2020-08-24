package com.whut.seven.dao;

import com.whut.seven.entity.PayUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BackPayUnitDao extends JpaRepository<PayUnit,Long>, JpaSpecificationExecutor<PayUnit> {
    @Query(nativeQuery = true,value="select distinct t.campus  from t_pay_unit t")
    List<String> listCampus();

    
//    List<String> findDistinctByCampus();
    
    @Query(nativeQuery = true,value="select distinct t.building_no from t_pay_unit t")
    List<String> listBuildingNo();

    @Query(nativeQuery = true,value="select * from t_pay_unit t where t.campus=?1 and t.building_no=?2")
    List<PayUnit> listPayUnit(String campus, String building_no);

    @Query(nativeQuery = true,value="SET foreign_key_checks = 0;")
    void closeForeignKeyCheck();

    @Query(nativeQuery = true,value="SET foreign_key_checks = 1;")
    void openForeignKeyCheck();
}
