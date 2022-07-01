package com.example.buildingsapartmentsowners.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    @Column(name = "name")
    private String name;
   @ManyToOne
   @JoinColumn(name= "apartment_id")
    private Apartment apartment;

}
