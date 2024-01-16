package com.example.DeliveryApp.request.orderRequest;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {

    private Long userId;
    private Long restaurantId;
    private Long driverId;
    private Long addressId;
    private List<OrderItemRequest> orderItems;
}
