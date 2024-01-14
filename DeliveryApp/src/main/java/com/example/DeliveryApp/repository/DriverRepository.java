package com.example.DeliveryApp.repository;

import com.example.DeliveryApp.entity.Driver;
import com.example.DeliveryApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    Optional<Driver> findByEmail(String email);
}