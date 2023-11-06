package com.dm.planetradehub.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Aircraft {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "type")
    private String type;
    @Basic
    @Column(name = "brand")
    private String brand;
    @Basic
    @Column(name = "model")
    private String model;
    @Basic
    @Column(name = "run")
    private int run;
    @Basic
    @Column(name = "year")
    private Object year;
    @Basic
    @Column(name = "color")
    private String color;
    @OneToOne(mappedBy = "aircraft")
    private Advertisement advertisement;
}
