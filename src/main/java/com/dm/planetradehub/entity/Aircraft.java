package com.dm.planetradehub.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(exclude = {"type", "manufacturer", "model"})
@ToString(exclude = {"type", "manufacturer", "model"})
public class Aircraft {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id", referencedColumnName = "id", nullable = false)
    private Type type;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manufacturer_id", referencedColumnName = "id", nullable = false)
    private Manufacturer manufacturer;
    @OneToOne(cascade = CascadeType.ALL)
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
