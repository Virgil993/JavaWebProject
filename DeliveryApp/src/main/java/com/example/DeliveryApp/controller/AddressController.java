package com.example.DeliveryApp.controller;


import com.example.DeliveryApp.request.addressRequest.AddressRequest;
import com.example.DeliveryApp.response.addressResponse.DeleteAddressResponse;
import com.example.DeliveryApp.response.addressResponse.AddressResponse;
import com.example.DeliveryApp.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping()
    public ResponseEntity<AddressResponse> createAddress(@RequestBody AddressRequest request){
        return ResponseEntity.ok(addressService.createAddress(request));
    }

    @GetMapping("/all/{userId}")
    public ResponseEntity<List<AddressResponse>> getAddressesByUser(@PathVariable Long userId){
        return ResponseEntity.ok(addressService.getAddressesByUser(userId));
    }

    @GetMapping("/single/{addressId}")
    public ResponseEntity<AddressResponse> getAddressById(@PathVariable Long addressId){
        return ResponseEntity.ok(addressService.getAddressById(addressId));
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<DeleteAddressResponse> deleteAddress(@PathVariable Long addressId){
        return ResponseEntity.ok(addressService.deleteAddress(addressId));
    }
}
