package com.example.buildingsapartmentsowners.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@AllArgsConstructor
@Data
@Builder
public class BuildingRequestDTO {

    private String street;
    private String house;

}
