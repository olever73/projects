package com.example.buildingsapartmentsowners.dto.bulk;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@AllArgsConstructor
@Data
public class ApartmentBulkDTO {
    private Integer apartmentNumber;

    private Boolean hasBalcony;

    private List<OwnerBulkDTO> owners;

}
