package com.example.DeliveryApp.controller;

import com.example.DeliveryApp.request.itemRequest.ItemRequest;
import com.example.DeliveryApp.response.itemResponse.DeleteItemResponse;
import com.example.DeliveryApp.response.itemResponse.ItemResponse;
import com.example.DeliveryApp.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping()
    public ResponseEntity<ItemResponse> createItem(@RequestBody ItemRequest request){
        return ResponseEntity.ok(itemService.createItem(request));
    }

    @GetMapping("/{menuId}")
    public ResponseEntity<List<ItemResponse>> getItemsByMenu(@PathVariable Long menuId){
        return ResponseEntity.ok(itemService.getItemsByMenu(menuId));
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<DeleteItemResponse> deleteItem(@PathVariable Long itemId){
        return ResponseEntity.ok(itemService.deleteItem(itemId));
    }
}