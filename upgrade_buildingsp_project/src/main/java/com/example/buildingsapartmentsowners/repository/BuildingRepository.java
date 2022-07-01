package com.example.buildingsapartmentsowners.repository;

import com.example.buildingsapartmentsowners.entity.Building;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuildingRepository extends JpaRepository<Building,Long> {

   Building findBuildingsByStreetAndHouse(String street,String house);

   List<Building> findBuildingsByStreet(String street);

   Boolean existsByStreetAndHouse(String street, String house);

   Building findBuildingByHouseAndStreet(String house, String street);
}
