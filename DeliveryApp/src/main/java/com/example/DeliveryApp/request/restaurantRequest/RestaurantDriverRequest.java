package com.example.DeliveryApp.request.restaurantRequest;

import lombok.Data;

@Data
public class RestaurantDriverRequest {
    private Long restaurantId;
    private Long driverId;
}
