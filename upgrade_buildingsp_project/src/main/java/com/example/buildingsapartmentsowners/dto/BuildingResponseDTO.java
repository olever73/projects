package com.example.buildingsapartmentsowners.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class BuildingResponseDTO {
    private Long id;
    private String address;
    private Integer countApartments;
}
