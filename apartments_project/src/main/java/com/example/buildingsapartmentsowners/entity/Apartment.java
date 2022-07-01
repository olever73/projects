package com.example.buildingsapartmentsowners.entity;

import lombok.*;
import org.hibernate.annotations.LazyToOne;

import javax.persistence.*;



@Entity
@Table(name = "apartment")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "number")
    private Integer apartmentNumber;
    @Column(name = "balcony")
    private Boolean  hasBalcony;
    // FK -> Join Column
    @JoinColumn(name = "building_id")
    @ManyToOne
    private Building building;



}