package com.whut.seven.dao;

import com.whut.seven.entity.PayUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PayUnitDao extends JpaRepository<PayUnit, String> {

    @Query(value = "SELECT distinct p.campus from PayUnit p")
    List<String> findAllCampus();

    @Query(value = "select distinct p.buildingNo from PayUnit p where p.campus = ?1 ")
    List<String> findAllBuildingsByCampus(String campus);

    @Query(value = "select distinct p.dormitoryNo from PayUnit p where p.buildingNo = ?1 ")
    List<String> findAllDormitoriesByBuilding(String building);

    PayUnit findByCampusAndBuildingNoAndDormitoryNo(String campus,String buildingNo,String dormitoryNo);
}
