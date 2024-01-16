package com.example.DeliveryApp.response.orderResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderItemResponse {
    private Long id;
    private Long itemId;
    private Integer quantity;
}
