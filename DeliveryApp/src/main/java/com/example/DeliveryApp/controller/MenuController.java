package com.example.DeliveryApp.controller;

import com.example.DeliveryApp.request.menuRequest.MenuRequest;
import com.example.DeliveryApp.request.menuRequest.UpdateMenuRequest;
import com.example.DeliveryApp.response.menuResponse.DeleteMenuResponse;
import com.example.DeliveryApp.response.menuResponse.MenuResponse;
import com.example.DeliveryApp.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menus")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @PostMapping()
    public ResponseEntity<MenuResponse> createMenu(@RequestBody MenuRequest request){
        return ResponseEntity.ok(menuService.createMenu(request));
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<List<MenuResponse>> getMenusByRestaurant(@PathVariable Long restaurantId){
        return ResponseEntity.ok(menuService.getMenusByRestaurant(restaurantId));
    }

    @PutMapping("/{menuId}/availability")
    public ResponseEntity<MenuResponse> updateMenuAvailability(@PathVariable Long menuId, @RequestBody UpdateMenuRequest request){
        return ResponseEntity.ok(menuService.updateMenuAvailability(menuId,request));
    }

    @DeleteMapping("/{menuId}")
    public ResponseEntity<DeleteMenuResponse> deleteMenu(@PathVariable Long menuId){
        return ResponseEntity.ok(menuService.deleteMenu(menuId));
    }
}
