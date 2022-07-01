package com.example.buildingsapartmentsowners.controller;


import com.example.buildingsapartmentsowners.dto.ApartmentResponseDTO;
import com.example.buildingsapartmentsowners.dto.BuildingRequestDTO;

import com.example.buildingsapartmentsowners.dto.BuildingResponseDTO;
import com.example.buildingsapartmentsowners.dto.bulk.BuildingBulkDTO;
import com.example.buildingsapartmentsowners.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BuildingController {


        @Autowired
        private BuildingService buildingService;

        @PostMapping("/buildings")
        @ResponseStatus(HttpStatus.CREATED)
        public void create(@RequestParam(name = "apartmentsCount") Integer apartmentsCount,
                           @RequestBody BuildingRequestDTO buildingDto) {
                buildingService.create(buildingDto, apartmentsCount);
        }

        @GetMapping("/buildings")
        @ResponseStatus(HttpStatus.OK)
        public List<BuildingResponseDTO> getBuildingsByStreet(@RequestParam(name = "street") String street) {
                return buildingService.getBuildingsByStreet(street);
        }

        @GetMapping("/buildings/{id}/apartments")
        @ResponseStatus(HttpStatus.OK)
        public List<ApartmentResponseDTO> getApartmentsOfBuilding(
                @PathVariable(name = "id") Long id,
                @RequestParam(name = "hasOwners", required = false) Optional<Boolean> hasOwners) {

                return hasOwners.isPresent() && hasOwners.get() ?
                        buildingService.getApartmentsWithOwnersById(id) :
                        buildingService.getApartmentsByBuildingId(id);
        }


        @PutMapping("/buildings/{buildingId}/apartments/{apartmentId}/owners/{ownerId}")
        @ResponseStatus(HttpStatus.ACCEPTED)
        public void moveOwnerToApartment(@PathVariable("buildingId") Long buildingId,
                                         @PathVariable("apartmentId") Long apartmentId,
                                         @PathVariable("ownerId") Long ownerId) {
                buildingService.moveAnOwner(buildingId, apartmentId, ownerId);
        }


        @DeleteMapping("/buildings/{buildingId}/apartments/{apartmentId}/owners/{ownerId}")
        @ResponseStatus(HttpStatus.ACCEPTED)
        public void evictOwnerFromApartment(@PathVariable("buildingId") Long buildingId,
                                            @PathVariable("apartmentId") Long apartmentId,
                                            @PathVariable("ownerId") Long ownerId) {

                buildingService.evictOwnerFromApartment(buildingId, apartmentId, ownerId);
        }


        @DeleteMapping("/buildings/{id}/demolish")
        public void demolishBuilding(@PathVariable("id") Long buildingId) {
                buildingService.deleteBuilding(buildingId);
        }

        @PostMapping("/buildings/bulk")
        public void createBulk(@RequestBody List<BuildingBulkDTO> bulkDTOS) {
               buildingService.bulkCity(bulkDTOS);
        }




}
