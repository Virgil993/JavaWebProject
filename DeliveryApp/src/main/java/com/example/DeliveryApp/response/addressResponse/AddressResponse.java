package com.example.DeliveryApp.response.addressResponse;

import lombok.Data;

@Data
public class AddressResponse {
    private Long id;
    private Long userId;
    private String city;
    private String street;
    private String house;
    private String details;
}
