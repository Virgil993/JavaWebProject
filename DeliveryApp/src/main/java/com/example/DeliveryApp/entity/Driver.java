package com.example.DeliveryApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="DRIVERS")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String vehicle;
    private Integer age;
    private String deliveryExperience;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "DRIVER_RESTAURANT",
            joinColumns = @JoinColumn(name = "driverId"),
            inverseJoinColumns = @JoinColumn(name="restaurantId")
    )
    private Set<Restaurant> restaurants = new HashSet<>();
}

