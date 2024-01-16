package com.example.DeliveryApp.mapper;

import com.example.DeliveryApp.entity.*;
import com.example.DeliveryApp.response.orderResponse.OrderItemResponse;
import com.example.DeliveryApp.response.orderResponse.OrderResponse;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public static OrderResponse toResponse(Order order) {
        OrderResponse response = new OrderResponse();

        response.setId(order.getId());
        response.setRestaurantId(order.getRestaurantId());
        response.setDriverId(order.getDriverId());
        response.setAddressId(order.getAddressId());
        response.setUserId(order.getUserId());
        response.setOrderItems(order.getOrderItems().stream()
                .map(item -> new OrderItemResponse(item.getId(), item.getItem().getId(),item.getQuantity()))
                .collect(Collectors.toList()));

        return response;
    }

    public static Order toEntity(User user, Restaurant restaurant, Address address, Driver driver, List<OrderItem> items) {
        Order order = new Order();

        order.setUserId(user.getId());
        order.setAddressId(address.getId());
        order.setRestaurantId(restaurant.getId());
        order.setDriverId(driver.getId());
        order.setOrderItems(items);

        return order;
    }

}
