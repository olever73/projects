package com.example.buildingsapartmentsowners.repository;


import com.example.buildingsapartmentsowners.entity.Apartment;
import com.example.buildingsapartmentsowners.entity.Building;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

    List<Apartment> findApartmentsByBuilding(Building building);

    Integer countApartmentsByBuildingid(Long id);

    Apartment findApartmendById(Long apartmentId);

    List<Apartment> findApartmentsByBuildingId(Long id);

    List<Apartment> findAllByBuildingIdAndIdIsIn(Long buildingId, List<Long> apartmentIds);

    Apartment findApartmentByIdAndBuildingId(Long apartmentId, Long buildingId);

    Apartment findApartmentByBuildingIdAndApartmentNumber(Long buildingId, Integer apartmentNumber);












}
