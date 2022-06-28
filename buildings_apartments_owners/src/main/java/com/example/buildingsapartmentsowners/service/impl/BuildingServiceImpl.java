package com.example.buildingsapartmentsowners.service.impl;


import com.example.buildingsapartmentsowners.dto.ApartmentResponseDTO;
import com.example.buildingsapartmentsowners.dto.BuildingRequestDTO;
import com.example.buildingsapartmentsowners.dto.BuildingResponseDTO;
import com.example.buildingsapartmentsowners.dto.OwnerResponseDTO;
import com.example.buildingsapartmentsowners.dto.bulk.ApartmentBulkDTO;
import com.example.buildingsapartmentsowners.dto.bulk.BuildingBulkDTO;
import com.example.buildingsapartmentsowners.dto.bulk.OwnerBulkDTO;
import com.example.buildingsapartmentsowners.entity.Apartment;
import com.example.buildingsapartmentsowners.entity.Building;
import com.example.buildingsapartmentsowners.entity.Owner;
import com.example.buildingsapartmentsowners.repository.ApartmentRepository;
import com.example.buildingsapartmentsowners.repository.BuildingRepository;
import com.example.buildingsapartmentsowners.repository.OwnerRepository;
import com.example.buildingsapartmentsowners.service.BuildingService;
import com.example.buildingsapartmentsowners.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.web.server.ResponseStatusException;


import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;


    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private OwnerService ownerService;


    @Override
    public void create(BuildingRequestDTO buildingDTO, int apartmentsCount) {
        if (buildingRepository.findBuildingsByStreetAndHouse(buildingDTO.getStreet(), buildingDTO.getHouse()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    String.format(String.format("'%s' street already exists!",
                            buildingDTO.getStreet()), "The building with number '%s' on the ", buildingDTO.getHouse()));
        }
        buildingRepository.save(
                Building.builder()
                        .street(buildingDTO.getStreet())
                        .house(buildingDTO.getHouse())
                        .build());
        Building building = buildingRepository.findBuildingsByStreetAndHouse(buildingDTO.getStreet(), buildingDTO.getHouse());
        for (int i = 0; i < apartmentsCount; i++) {
            apartmentRepository.save(Apartment.builder().building(building).build());
        }
    }

    @Override
    public List<BuildingResponseDTO> getBuildingsByStreet(String street) {

        return buildingRepository.findBuildingsByStreet(street)
                .stream()
                .map(this::convertBuildingToDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<ApartmentResponseDTO> getApartmentsWithOwnersById(Long buildingId) {
        return apartmentRepository
                .findAllByBuildingIdAndIdIsIn(
                        buildingId,
                        ownerService.findOwnersIdsWithApartmentNotNull())
                .stream()
                .map(this::convertApartmentToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ApartmentResponseDTO> getApartmentsByBuildingId(Long buildingId) {

        Building building = findBuildingById(buildingId);

        return apartmentRepository.findApartmentsByBuilding(building)
                .stream()
                .map(this::convertApartmentToDto)
                .collect(Collectors.toList());
    }

    private ApartmentResponseDTO convertApartmentToDto(Apartment apartment) {
        return ApartmentResponseDTO
                .builder()
                .apartmentNumber(apartment.getApartmentNumber())
                .id(apartment.getId())
                .hasBalcony(apartment.getHasBalcony())
                .owners(convertListOwnersToListDto(
                        ownerRepository.findOwnersByApartmentId(apartment.getId())))
                .build();
    }

    private Building findBuildingById(Long buildingId) {
        return buildingRepository.findById(buildingId).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("The building with id [%s] doesn't exist", buildingId))
        );
    }


    private Apartment getApartmentByApartmentId(Long id) {
        return apartmentRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Apartment with id [%s] doesnt exist", id))
        );
    }

    @Override
    public void moveAnOwner(Long buildingId, Long apartmentId, Long ownerId) {

        Building building = findBuildingById(buildingId); // для not found
        Apartment apartment = getApartmentByApartmentId(apartmentId); // для not fount

        if (apartment.getBuilding().getId().equals(buildingId)) {
            Owner owner = getOwnerByOwnerId(ownerId);
            owner.setApartment(apartment);
            ownerRepository.save(owner);
        } else
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    String.format("The apartment with id [%s] isn't in the building with id ", apartmentId)
                            + String.format("[%s]", buildingId));
    }

    @Override
    public void evictOwnerFromApartment(Long buildingId, Long apartmentId, Long ownerId) {
        Owner owner = ownerRepository.
                findOwnerByIdAndApartmentId(
                        ownerId,
                        apartmentRepository.findApartmentByIdAndBuildingId(
                                        apartmentId,
                                        findBuildingById(buildingId).getId())
                                .getId()
                );
        owner.setApartment(null);
        ownerRepository.save(owner);

    }

    @Override
    public void deleteBuilding(Long buildingId) {
        Building building = findBuildingById(buildingId);

        List<Apartment> apartments = apartmentRepository.findApartmentsByBuildingId(buildingId);
        List<Long> apartmentsId = apartments
                .stream()
                .map(Apartment::getId)
                .collect(Collectors.toList());

        List<Long> ownersId = ownerRepository.findOwnersByApartmentIsIn(apartments)
                .stream()
                .map(Owner::getId)
                .collect(Collectors.toList());

        ownerRepository.deleteAllById(ownersId);
        apartmentRepository.deleteAllById(apartmentsId);

        buildingRepository.delete(building);
    }




    private BuildingResponseDTO convertBuildingToDto(Building building) {
        return BuildingResponseDTO.builder()
                .id(building.getId())
                .address(building.getStreet() + "" + building.getHouse())
                .countApartments(apartmentRepository.countApartmentsByBuildingid((building.getId())))
                .build();
    }

    private List<OwnerResponseDTO> convertListOwnersToListDto(List<Owner> owners) {
        return owners
                .stream()
                .map(this::convertOwnerToDto)
                .collect(Collectors.toList());
    }

    private OwnerResponseDTO convertOwnerToDto(Owner owner) {
        return OwnerResponseDTO
                .builder()
                .name(owner.getName())
                .apartmentId(owner.getApartment().getId())
                .build();
    }

    private Owner getOwnerByOwnerId(Long id) {
        return ownerRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("There is no owner with id [%s]", id))
        );
    }
   //(propagation = Propagation.REQUIRES_NEW)
    @Override
    @Transactional
    public void bulkCity(List<BuildingBulkDTO> bulkCity) {
        for (var building : bulkCity) {
            createBuildingForBulk(building);
        }
    }

    private void createBuildingForBulk(BuildingBulkDTO building) {

        if (buildingRepository.existsByStreetAndHouse(building.getStreet(), building.getHouse())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    String.format("Building with number [%s] and street name ", building.getHouse()
                            + String.format("[%s] already exists", building.getStreet())));
        }

        buildingRepository.save(convertBulkDtoToBuilding(building));

        Building savedBuilding = buildingRepository
                .findBuildingByHouseAndStreet(building.getHouse(), building.getStreet());

        for (var apartment : building.getApartments()) {

            var apartmentToSave = convertToApartmentEntity(apartment, savedBuilding);
            apartmentRepository.save(apartmentToSave);
            Apartment savedApartment = apartmentRepository.findApartmentByBuildingIdAndApartmentNumber(
                    savedBuilding.getId(), apartment.getApartmentNumber()
            );

            var owners = convertBulkToOwners(apartment.getOwners(), savedApartment);
            ownerRepository.saveAll(owners);

        }
    }

    public  Building convertBulkDtoToBuilding(BuildingBulkDTO building) {
        return Building
                .builder()
                .house(building.getHouse())
                .street(building.getStreet())
                .build();
    }

    public  Apartment convertToApartmentEntity(ApartmentBulkDTO dto, Building building) {
        return Apartment.builder()
                .apartmentNumber(dto.getApartmentNumber())
                .hasBalcony(dto.getHasBalcony())
                .building(building)
                .build();
    }

    public static List<Owner> convertBulkToOwners(List<OwnerBulkDTO> ownerdtos, Apartment apartment){
        return ownerdtos
                .stream()
                .map(owner -> Owner
                        .builder()
                        .name(owner.getName())
                        .apartment(apartment)
                        .build()
                )
                .collect(Collectors.toList());
    }

}


