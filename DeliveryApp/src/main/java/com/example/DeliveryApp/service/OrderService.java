package com.example.DeliveryApp.service;

import com.example.DeliveryApp.entity.*;
import com.example.DeliveryApp.exception.addressException.AddressDoesNotExistException;
import com.example.DeliveryApp.exception.driverException.DriverDoesNotExistException;
import com.example.DeliveryApp.exception.itemException.ItemDoesNotExistException;
import com.example.DeliveryApp.exception.restaurantException.RestaurantDoesNotExistException;
import com.example.DeliveryApp.exception.userException.UserDoesNotExistException;
import com.example.DeliveryApp.mapper.OrderMapper;
import com.example.DeliveryApp.repository.*;
import com.example.DeliveryApp.request.orderRequest.OrderRequest;
import com.example.DeliveryApp.response.orderResponse.DeleteOrderResponse;
import com.example.DeliveryApp.response.orderResponse.OrderResponse;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private ItemRepository itemRepository;

    public OrderResponse createOrder(OrderRequest request){
        User user = userRepository.findById(request.getUserId()).orElseThrow(()-> new UserDoesNotExistException("User not found with this id"));
        Driver driver = driverRepository.findById(request.getDriverId()).orElseThrow(()-> new DriverDoesNotExistException("Driver not found with this id"));
        Restaurant restaurant = restaurantRepository.findById(request.getRestaurantId()).orElseThrow(()-> new RestaurantDoesNotExistException("Restaurant not found with this id"));
        Address address = addressRepository.findById(request.getAddressId()).orElseThrow(()-> new AddressDoesNotExistException("Address not found with this id"));

        List<OrderItem> items = request.getOrderItems().stream().map(itemRequest -> {
            Item item = itemRepository.findById(itemRequest.getItemId()).orElseThrow(()-> new ItemDoesNotExistException("Item not found with id"));
            OrderItem orderItem = new OrderItem();
            orderItem.setItem(item);
            orderItem.setQuantity(itemRequest.getQuantity());
            return orderItem;
        }).collect(Collectors.toList());

        Order order = OrderMapper.toEntity(user, restaurant,address, driver,items);
        order = orderRepository.save(order);
        return OrderMapper.toResponse(order);
    }

    public OrderResponse updateOrder(Long id, OrderRequest request) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found for id: " + id));

        List<OrderItem> items = request.getOrderItems().stream()
                .map(itemRequest -> {
                    Item item = itemRepository.findById(itemRequest.getItemId())
                            .orElseThrow(() -> new RuntimeException("Item not found"));
                    OrderItem orderItem = new OrderItem();
                    orderItem.setItem(item);
                    orderItem.setQuantity(itemRequest.getQuantity());
                    return orderItem;
                }).collect(Collectors.toList());

        existingOrder.getOrderItems().clear();
        for(var i=0;i< items.size();i++){
            existingOrder.getOrderItems().add(items.get(i));
        }

        orderRepository.save(existingOrder);
        return OrderMapper.toResponse(existingOrder);
    }

    public List<OrderResponse> getOrdersByUserId(Long userId){
        return orderRepository.findAllByUserId(userId).stream().map(OrderMapper::toResponse).collect(Collectors.toList());
    }

    public List<OrderResponse> getOrdersByRestaurantId(Long restaurantId){
        return orderRepository.findAllByRestaurantId(restaurantId).stream().map(OrderMapper::toResponse).collect(Collectors.toList());
    }
    public List<OrderResponse> getOrdersByDriverId(Long driverId){
        return orderRepository.findAllByDriverId(driverId).stream().map(OrderMapper::toResponse).collect(Collectors.toList());
    }

    public DeleteOrderResponse deleteOrder(Long orderId){
        if (!orderRepository.existsById(orderId)) {
            throw new RuntimeException("Order not found for id: " + orderId);
        }

        orderRepository.deleteById(orderId);
        DeleteOrderResponse response = new DeleteOrderResponse();
        response.setMessage("Order deleted successfully");
        return response;
    }


}
