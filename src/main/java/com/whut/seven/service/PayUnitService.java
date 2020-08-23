package com.whut.seven.service;

import java.util.List;

public interface PayUnitService {
    List<String> findAllCampus();

    List<String> findAllBuildingsByCampus(String campus);

    List<String> findAllDormitoriesByBuilding(String building);
}
