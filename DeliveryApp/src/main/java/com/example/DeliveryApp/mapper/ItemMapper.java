package com.example.DeliveryApp.mapper;

import com.example.DeliveryApp.entity.Item;
import com.example.DeliveryApp.entity.Menu;
import com.example.DeliveryApp.entity.Restaurant;
import com.example.DeliveryApp.request.itemRequest.ItemRequest;
import com.example.DeliveryApp.response.itemResponse.ItemResponse;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public static ItemResponse toResponse(Item item) {
        ItemResponse response = new ItemResponse();

        response.setId(item.getId());
        response.setName(item.getName());
        response.setPrice(item.getPrice());
        response.setMenuId(item.getMenu().getId());

        return response;
    }


    public static Item toEntity(ItemRequest request, Menu menu) {
        Item item = new Item();

        item.setName(request.getName());
        item.setPrice(request.getPrice());
        item.setMenu(menu);

        return item;
    }
}
