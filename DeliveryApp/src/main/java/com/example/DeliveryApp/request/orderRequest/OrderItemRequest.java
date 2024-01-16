package com.example.DeliveryApp.request.orderRequest;

import lombok.Data;

@Data
public class OrderItemRequest {

    private Long itemId;
    private Integer quantity;
}
