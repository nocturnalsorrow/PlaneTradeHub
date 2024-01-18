package com.dm.planetradehub.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.List;

@Entity
@Data
public class Manufacturer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id", nullable = false)
    private Type type;
    @OneToOne(mappedBy = "manufacturer")
    private Aircraft aircraft;
    @OneToMany(mappedBy = "manufacturer")
    private List<Model> model;
}
