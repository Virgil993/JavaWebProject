package com.example.DeliveryApp.request.itemRequest;

import lombok.Data;

@Data
public class ItemRequest {
    private Long menuId;
    private String name;
    private Float price;
}
