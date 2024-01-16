package com.example.DeliveryApp.request.menuRequest;

import lombok.Data;

@Data
public class MenuRequest {
    private Long restaurantId;
    private String name;
    private Boolean availability;
}
