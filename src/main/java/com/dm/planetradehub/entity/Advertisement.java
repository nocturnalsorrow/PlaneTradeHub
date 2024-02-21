package com.dm.planetradehub.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Data
@EqualsAndHashCode(exclude = {"user", "aircraft", "advertisement"})
@ToString(exclude = {"user", "aircraft", "advertisement"})
public class Advertisement {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "aircraft_id", referencedColumnName = "id", nullable = false)
    private Aircraft aircraft;
    @Basic
    @Column(name = "condition")
    private String condition;
    @Basic
    @Column(name = "price")
    private int price;
    @Basic
    @Column(name = "region")
    private String region;
    @Basic
    @Column(name = "city")
    private String city;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "publication_date")
    private Date publicationDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "advertisement")
    private List<Gallery> images = new ArrayList<>();
}
