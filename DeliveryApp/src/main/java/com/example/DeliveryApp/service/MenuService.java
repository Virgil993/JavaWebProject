package com.example.DeliveryApp.service;

import com.example.DeliveryApp.entity.Menu;
import com.example.DeliveryApp.entity.Restaurant;
import com.example.DeliveryApp.exception.menuException.MenuDoesNotExistException;
import com.example.DeliveryApp.exception.menuException.MenuValidationException;
import com.example.DeliveryApp.exception.restaurantException.RestaurantDoesNotExistException;
import com.example.DeliveryApp.mapper.MenuMapper;
import com.example.DeliveryApp.mapper.RestaurantMapper;
import com.example.DeliveryApp.repository.ItemRepository;
import com.example.DeliveryApp.repository.MenuRepository;
import com.example.DeliveryApp.repository.RestaurantRepository;
import com.example.DeliveryApp.request.menuRequest.MenuRequest;
import com.example.DeliveryApp.request.menuRequest.UpdateMenuRequest;
import com.example.DeliveryApp.response.menuResponse.DeleteMenuResponse;
import com.example.DeliveryApp.response.menuResponse.MenuResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ItemRepository itemRepository;

    public MenuResponse createMenu(MenuRequest request){
        if(request.getName().isBlank() || request.getRestaurantId() == null){
            throw new MenuValidationException("Name and restaurant Id are mandatory");
        }
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(request.getRestaurantId());
        if(restaurantOptional.isEmpty()){
            throw new RestaurantDoesNotExistException("Restaurant doesn't exist with this Id");
        }
        final var restaurant=restaurantOptional.get();
        Menu menu = MenuMapper.toEntity(request,restaurant);
        menu = menuRepository.save(menu);
        return MenuMapper.toResponse(menu);
    }

    public List<MenuResponse> getMenusByRestaurant(Long restaurantId){
            Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
            if(restaurantOptional.isEmpty()){
                throw new RestaurantDoesNotExistException("Restaurant doesn't exist with this Id");
            }
            List<Menu> menus = menuRepository.findByRestaurantId(restaurantId);
            List<MenuResponse> response = new ArrayList<>();
            for(var i=0;i<menus.size();i++){
                response.add(MenuMapper.toResponse(menus.get(i)));
            }
            return response;

    }

    public MenuResponse updateMenuAvailability(Long menuId, UpdateMenuRequest request){
        Optional<Menu> optionalMenu = menuRepository.findById(menuId);

        if(optionalMenu.isEmpty()){
            throw new MenuDoesNotExistException("Menu does not exist with this Id");
        }
        Menu menu = optionalMenu.get();

        if(request.getAvailability()!=null){
            menu.setAvailability(request.getAvailability());
        }
        else{
            menu.setAvailability(false);
        }
        menu = menuRepository.save(menu);
        return MenuMapper.toResponse(menu);

    }

    @Transactional
    public DeleteMenuResponse deleteMenu(Long menuId){
        Optional<Menu> optionalMenu = menuRepository.findById(menuId);

        if(optionalMenu.isEmpty()){
            throw new MenuDoesNotExistException("Menu does not exist with this Id");
        }
        itemRepository.deleteByMenuId(menuId);
        menuRepository.deleteById(menuId);

        DeleteMenuResponse response = new DeleteMenuResponse();
        response.setMessage("Menu deleted successfully!");
        return response;
    }


}
