package com.example.buildingsapartmentsowners.controller;

import com.example.buildingsapartmentsowners.dto.OwnerRequestDTO;
import com.example.buildingsapartmentsowners.dto.OwnerResponseDTO;
import com.example.buildingsapartmentsowners.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OwnerController {

    @Autowired
    private OwnerService service;

    @PostMapping("/owners")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody OwnerRequestDTO ownerDto) {
        service.createOwner(ownerDto);
    }

    @PutMapping("/owners/{id}")
    public void update(@PathVariable("id") Long id,
                     @RequestBody OwnerRequestDTO ownerDto) {
        service.update(id, ownerDto);
    }

    @GetMapping("/owners")
    public List<OwnerResponseDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/owners/{id}")
    public OwnerResponseDTO getById(@PathVariable("id") Long id) {
        return service.getOwnerById(id);
    }
}