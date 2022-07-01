package com.example.buildingsapartmentsowners.service;


import com.example.buildingsapartmentsowners.dto.ApartmentRequestDTO;
import com.example.buildingsapartmentsowners.dto.ApartmentResponseDTO;

import java.util.List;

public interface ApartmentService {

    void update(Long apartmentId, ApartmentRequestDTO apartmentDto);

    List<ApartmentResponseDTO> getAllApartments();

    ApartmentResponseDTO getApartmentById(Long id);
}
