package com.example.buildingsapartmentsowners.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "owner")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(unique = true)
    private String name;
   @ManyToOne
   @JoinColumn(name= "apartment_id")
    private Apartment apartment;

}
