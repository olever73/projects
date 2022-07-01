package com.example.buildingsapartmentsowners.service;

import com.example.buildingsapartmentsowners.dto.OwnerRequestDTO;
import com.example.buildingsapartmentsowners.dto.OwnerResponseDTO;

import java.util.List;

public interface OwnerService {


    void createOwner(OwnerRequestDTO ownerDTO);

     void update (long id, OwnerRequestDTO ownerDTO);

    List<OwnerResponseDTO>getAll();

    OwnerResponseDTO getOwnerById(Long id);


    List<Long> findOwnersIdsWithApartmentNotNull();
}
