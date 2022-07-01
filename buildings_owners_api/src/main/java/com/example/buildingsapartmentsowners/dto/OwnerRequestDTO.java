package com.example.buildingsapartmentsowners.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;



    @AllArgsConstructor
    @Data
    @Builder
    public class OwnerRequestDTO {
        private Long apartmentId;
        private String name;

    }




