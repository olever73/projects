package com.example.buildingsapartmentsowners.service.impl;


import com.example.buildingsapartmentsowners.dto.ApartmentRequestDTO;
import com.example.buildingsapartmentsowners.dto.ApartmentResponseDTO;
import com.example.buildingsapartmentsowners.dto.OwnerResponseDTO;
import com.example.buildingsapartmentsowners.entity.Apartment;
import com.example.buildingsapartmentsowners.entity.Owner;
import com.example.buildingsapartmentsowners.repository.ApartmentRepository;

import com.example.buildingsapartmentsowners.repository.OwnerRepository;
import com.example.buildingsapartmentsowners.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApartamenServiceImpl implements ApartmentService {

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private OwnerRepository ownerRepository;


    @Override
    public void update(Long apartmentId, ApartmentRequestDTO apartmentDTO) {
                     Apartment apartment= apartmentRepository.findApartmendById(apartmentId);
                     apartment.setApartmentNumber(apartmentDTO.getApartmentNumber());
                     apartment.setHasBalcony(apartmentDTO.getHasBalcony());
                     apartmentRepository.save(apartment);
    }

    @Override
    public List<ApartmentResponseDTO> getAllApartments() {
        return apartmentRepository.findAll()
                .stream()
                .map(this::convertApartmentToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ApartmentResponseDTO getApartmentById(Long id) {
        Apartment apartment = findApartmentById(id);
        return convertApartmentToResponseDto(apartment);
    }


    private Apartment findApartmentById(Long id) {
        return apartmentRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Apartment with id [%s] doesnt exist", id))
        );
    }

    private ApartmentResponseDTO findApartmendById (Long id){
        Apartment apartment=findApartmendById(id);
        return  convertApartmentToResponseDTO(apartment);

    }

    private ApartmentResponseDTO convertApartmentToResponseDTO(Apartment apartment) {

        return ApartmentResponseDTO
                .builder()
                .id(apartment.getId())
                .apartmentNumber(apartment.getApartmentNumber())
                .hasBalcony(apartment.getHasBalcony())
                .owners(getOwnersByApartmentId(apartment.getId()))
                .build();
    }

    private List<OwnerResponseDTO> getOwnersByApartmentId(Long apartmentId) {
        return ownerRepository.findOwnersByApartmentId(apartmentId)
                .stream()
                .map(this::convertOwnerToDTO)
                .collect(Collectors.toList());

    }
    private OwnerResponseDTO convertOwnerToDTO(Owner owner) {
       return OwnerResponseDTO
                .builder()
                .name(owner.getName())
               .apartmentId(owner.getApartment().getId())
               .build();
    }
    private ApartmentResponseDTO convertApartmentToResponseDto(Apartment apartment) {
        return ApartmentResponseDTO
                .builder()
                .id(apartment.getId())
                .apartmentNumber(apartment.getApartmentNumber())
                .hasBalcony(apartment.getHasBalcony())
                .owners(getOwnersByApartmentId(apartment.getId()))
                .build();
    }
}


