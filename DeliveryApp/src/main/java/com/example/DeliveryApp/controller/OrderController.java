package com.example.DeliveryApp.controller;

import com.example.DeliveryApp.request.orderRequest.OrderRequest;
import com.example.DeliveryApp.request.restaurantRequest.RestaurantRequest;
import com.example.DeliveryApp.response.orderResponse.DeleteOrderResponse;
import com.example.DeliveryApp.response.orderResponse.OrderResponse;
import com.example.DeliveryApp.response.restaurantResponse.RestaurantResponse;
import com.example.DeliveryApp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping()
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest request){
        return ResponseEntity.ok(orderService.createOrder(request));
    }
    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable Long id, @RequestBody OrderRequest request) {
        return ResponseEntity.ok(orderService.updateOrder(id, request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteOrderResponse> deleteOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.deleteOrder(id));
    }

    @GetMapping("restaurant/{restaurantId}")
    public ResponseEntity<List<OrderResponse>> getOrdersByRestaurantId(@PathVariable Long restaurantId){
        return ResponseEntity.ok(orderService.getOrdersByRestaurantId(restaurantId));
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<List<OrderResponse>> getOrdersByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
    }
    @GetMapping("driver/{driverId}")
    public ResponseEntity<List<OrderResponse>> getOrdersByDriverId(@PathVariable Long driverId){
        return ResponseEntity.ok(orderService.getOrdersByDriverId(driverId));
    }
}
