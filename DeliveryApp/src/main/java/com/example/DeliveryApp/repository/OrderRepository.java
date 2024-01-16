package com.example.DeliveryApp.repository;

import com.example.DeliveryApp.entity.Order;
import com.example.DeliveryApp.response.orderResponse.OrderResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByUserId(Long userId);
    List<Order> findAllByRestaurantId(Long restaurantId);
    List<Order> findAllByDriverId(Long driverId);
}
