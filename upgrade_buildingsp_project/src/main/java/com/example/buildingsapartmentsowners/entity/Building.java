package com.example.buildingsapartmentsowners.entity;

import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Entity
@Table(name = "buildung")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "street")
    private String street;
    @Column(name = "house")
    private String house;


}