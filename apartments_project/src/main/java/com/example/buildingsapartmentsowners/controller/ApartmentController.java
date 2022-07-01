package com.example.buildingsapartmentsowners.controller;


import com.example.buildingsapartmentsowners.dto.ApartmentRequestDTO;
import com.example.buildingsapartmentsowners.dto.ApartmentResponseDTO;
import com.example.buildingsapartmentsowners.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class ApartmentController {


    @Autowired
    private ApartmentService apartmentService;




    @GetMapping("/apartments")
    public List<ApartmentResponseDTO>getAll(){
        return apartmentService.getAllApartments();
    }

    @GetMapping( "/apartments/{id}")
    void ApartmentResponseDTO(@PathVariable("id") Long id ){

        apartmentService.getApartmentById(id);
    }


    @PutMapping("/apartments/{id}")
    public void update(@PathVariable("id") Long id,
                       @RequestBody ApartmentRequestDTO apartmentDto) {
        apartmentService.update(id, apartmentDto);
    }
}
