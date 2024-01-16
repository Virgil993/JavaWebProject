package com.example.DeliveryApp.response.itemResponse;

import lombok.Data;

@Data
public class ItemResponse {
    private Long id;

    private Long menuId;
    private String name;
    private Float price;
}
