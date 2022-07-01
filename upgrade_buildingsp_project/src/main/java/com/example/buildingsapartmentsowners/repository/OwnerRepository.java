package com.example.buildingsapartmentsowners.repository;

import com.example.buildingsapartmentsowners.entity.Apartment;
import com.example.buildingsapartmentsowners.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

    List<Owner> findOwnersByApartmentIsNotNull();

    Owner findOwnerByIdAndApartmentId(Long ownerId, Long apartmentId);

    List<Owner> findOwnersByApartmentId(Long id);

    List<Owner> findOwnersByApartmentIsIn(List<Apartment> apartments);

    Boolean existsAllByName(List<String> names);

}
