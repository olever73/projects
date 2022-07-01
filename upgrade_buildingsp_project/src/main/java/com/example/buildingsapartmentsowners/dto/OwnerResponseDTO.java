package com.example.buildingsapartmentsowners.dto;

import com.example.buildingsapartmentsowners.entity.Apartment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;



import com.example.buildingsapartmentsowners.entity.Apartment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

    @AllArgsConstructor
    @Data
    @Builder
    public class OwnerResponseDTO {
        private Long apartmentId;
        private String name;

}
