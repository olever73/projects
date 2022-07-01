package com.example.buildingsapartmentsowners.dto;

import com.example.buildingsapartmentsowners.entity.Apartment;
import com.example.buildingsapartmentsowners.entity.Building;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class ApartmentResponseDTO extends Apartment {
    private Long id;
    private Integer apartmentNumber;
    private Boolean hasBalcony;

    @NonNull
    private List<OwnerResponseDTO> owners;

}
