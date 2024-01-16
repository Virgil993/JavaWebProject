package com.example.DeliveryApp.request.addressRequest;

import lombok.Data;

@Data
public class AddressRequest {
    private Long userId;
    private String city;
    private String street;
    private String house;
    private String details;
}
