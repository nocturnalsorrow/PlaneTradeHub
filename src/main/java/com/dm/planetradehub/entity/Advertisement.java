package com.dm.planetradehub.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Entity
@Data
@EqualsAndHashCode(exclude = {"user", "aircraft"})
@ToString(exclude = {"user", "aircraft"})
public class Advertisement {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
    @OneToOne
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
}
