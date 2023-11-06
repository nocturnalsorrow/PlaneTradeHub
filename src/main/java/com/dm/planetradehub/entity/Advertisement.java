package com.dm.planetradehub.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.mapping.ToOne;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
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
}
