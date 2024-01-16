package com.example.DeliveryApp.mapper;

import com.example.DeliveryApp.entity.Menu;
import com.example.DeliveryApp.entity.Restaurant;
import com.example.DeliveryApp.request.menuRequest.MenuRequest;
import com.example.DeliveryApp.response.menuResponse.MenuResponse;
import org.springframework.stereotype.Component;

@Component
public class MenuMapper {

    public static MenuResponse toResponse(Menu menu) {
        MenuResponse response = new MenuResponse();

        response.setId(menu.getId());
        response.setName(menu.getName());
        response.setAvailability(menu.getAvailability());
        response.setRestaurantId(menu.getRestaurant().getId());

        return response;
    }


    public static Menu toEntity(MenuRequest request, Restaurant restaurant) {
        Menu menu = new Menu();

        menu.setName(request.getName());
        if(request.getAvailability()!=null){
            menu.setAvailability(request.getAvailability());
        }
        else{
            menu.setAvailability(false);
        }
        menu.setRestaurant(restaurant);

        return menu;
    }
}
