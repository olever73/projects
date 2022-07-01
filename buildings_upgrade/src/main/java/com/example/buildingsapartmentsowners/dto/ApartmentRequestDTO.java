package com.example.buildingsapartmentsowners.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data

public class ApartmentRequestDTO {

    private Integer apartmentNumber;
    private Boolean hasBalcony;


}
