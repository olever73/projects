package com.example.buildingsapartmentsowners.dto.bulk;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class BuildingBulkDTO {

    private String street;

    private String house;

    private List<ApartmentBulkDTO> apartments;
}