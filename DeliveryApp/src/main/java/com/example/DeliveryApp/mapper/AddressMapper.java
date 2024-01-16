package com.example.DeliveryApp.mapper;

import com.example.DeliveryApp.entity.Address;
import com.example.DeliveryApp.entity.Restaurant;
import com.example.DeliveryApp.entity.User;
import com.example.DeliveryApp.request.addressRequest.AddressRequest;
import com.example.DeliveryApp.response.addressResponse.AddressResponse;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
    public static AddressResponse toResponse(Address address) {
        AddressResponse response = new AddressResponse();

        response.setId(address.getId());
        response.setCity(address.getCity());
        response.setHouse(address.getHouse());
        response.setDetails(address.getDetails());
        response.setStreet(address.getStreet());
        response.setUserId(address.getUser().getId());
        return response;
    }


    public static Address toEntity(AddressRequest request, User user) {
        Address address = new Address();

        address.setCity(request.getCity());
        address.setHouse(request.getHouse());
        address.setStreet(request.getStreet());
        address.setDetails(request.getDetails());
        address.setUser(user);

        return address;
    }
}
