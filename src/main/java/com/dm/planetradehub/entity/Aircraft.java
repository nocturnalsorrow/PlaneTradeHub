package com.dm.planetradehub.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Year;
import java.util.Date;

@Entity
@Data
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
    private Date year;
    @Basic
    @Column(name = "color")
    private String color;
    @OneToOne(mappedBy = "aircraft")
    private Advertisement advertisement;
}
