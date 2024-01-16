package com.example.DeliveryApp.response.orderResponse;

import com.example.DeliveryApp.request.orderRequest.OrderRequest;
import lombok.Data;

import java.util.List;

@Data
public class OrderResponse {

    private Long id;
    private Long userId;
    private Long restaurantId;
    private Long driverId;
    private Long addressId;
    private List<OrderItemResponse> orderItems;
}
