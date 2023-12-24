package com.dm.planetradehub.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Aircraft {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @OneToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id", nullable = false)
    private Type type;
    @OneToOne
    @JoinColumn(name = "manufacturer_id", referencedColumnName = "id", nullable = false)
    private Manufacturer manufacturer;
    @OneToOne
    @JoinColumn(name = "model_id", referencedColumnName = "id", nullable = false)
    private Model model;
    @Basic
    @Column(name = "run")
    private int run;
    @Basic
    @Column(name = "year")
    private int year;
    @Basic
    @Column(name = "color")
    private String color;
    @OneToOne(mappedBy = "aircraft")
    private Advertisement advertisement;
}
