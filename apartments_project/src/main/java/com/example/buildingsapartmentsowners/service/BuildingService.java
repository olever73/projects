package com.example.buildingsapartmentsowners.service;



import com.example.buildingsapartmentsowners.dto.ApartmentResponseDTO;
import com.example.buildingsapartmentsowners.dto.BuildingRequestDTO;
import com.example.buildingsapartmentsowners.dto.BuildingResponseDTO;

import java.util.List;


public interface BuildingService {



    void create(BuildingRequestDTO buildingDto, int apartmentsCount);

    List<BuildingResponseDTO> getBuildingsByStreet(String street);

    List<ApartmentResponseDTO> getApartmentsWithOwnersById(Long buildingId);

    List<ApartmentResponseDTO> getApartmentsByBuildingId(Long buildingId);

    void moveAnOwner(Long buildingId, Long apartmentId, Long ownerId);

    void evictOwnerFromApartment(Long buildingId, Long apartmentId, Long ownerId);

    void deleteBuilding(Long buildingId);

}