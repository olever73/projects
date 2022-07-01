package com.example.buildingsapartmentsowners.service.impl;


import com.example.buildingsapartmentsowners.dto.OwnerRequestDTO;
import com.example.buildingsapartmentsowners.dto.OwnerResponseDTO;
import com.example.buildingsapartmentsowners.entity.Owner;
import com.example.buildingsapartmentsowners.repository.ApartmentRepository;
import com.example.buildingsapartmentsowners.repository.OwnerRepository;
import com.example.buildingsapartmentsowners.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    OwnerRepository ownerRepository;
    @Autowired
    private ApartmentRepository apartmentRepository;

    @Override
    public void createOwner(OwnerRequestDTO ownerDTO) {
        Owner owner= Owner.builder()
                        .name(ownerDTO.getName())
                .apartment(apartmentRepository.findById(ownerDTO.getApartmentId()).orElseThrow())
                                .build();
                ownerRepository.save(owner);


    }

    @Override
    public void update(long id, OwnerRequestDTO ownerDTO) {
             Owner owner=findOwnerById(id);
                     owner.setName(ownerDTO.getName());
                     ownerRepository.save(owner);
    }

    private Owner findOwnerById(long id) {
        return ownerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Owner with id [%s] doesn't exist!", id))
        );
    }

    @Override
    public List<OwnerResponseDTO> getAll() {
        return ownerRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

    }

    @Override
    public OwnerResponseDTO getOwnerById(Long id) {

        return convertToDTO(findOwnerById(id));
    }

    @Override
    public List<Long> findOwnersIdsWithApartmentNotNull() {
        return ownerRepository.findOwnersByApartmentIsNotNull()
                .stream()
                .map(x -> x.getApartment().getId())
                .collect(Collectors.toList());

    }




    private OwnerResponseDTO convertToDTO(Owner owner){
        return OwnerResponseDTO.builder()
                .name(owner.getName())
                .build();
    }

}
