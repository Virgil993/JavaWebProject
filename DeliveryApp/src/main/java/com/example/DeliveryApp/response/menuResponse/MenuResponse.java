package com.example.DeliveryApp.response.menuResponse;

import lombok.Data;

@Data
public class MenuResponse {
    private Long id;

    private String name;
    private Boolean availability;
    private Long restaurantId;
}
