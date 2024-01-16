package com.example.DeliveryApp.service;

import com.example.DeliveryApp.entity.Item;
import com.example.DeliveryApp.entity.Menu;
import com.example.DeliveryApp.exception.itemException.ItemDoesNotExistException;
import com.example.DeliveryApp.exception.itemException.ItemValidationException;
import com.example.DeliveryApp.exception.menuException.MenuDoesNotExistException;
import com.example.DeliveryApp.mapper.ItemMapper;
import com.example.DeliveryApp.mapper.MenuMapper;
import com.example.DeliveryApp.repository.ItemRepository;
import com.example.DeliveryApp.repository.MenuRepository;
import com.example.DeliveryApp.request.itemRequest.ItemRequest;
import com.example.DeliveryApp.response.itemResponse.DeleteItemResponse;
import com.example.DeliveryApp.response.itemResponse.ItemResponse;
import com.example.DeliveryApp.response.menuResponse.DeleteMenuResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private MenuRepository menuRepository;

    public ItemResponse createItem(ItemRequest request){
        if(request.getMenuId() == null || request.getName() == null || request.getPrice() == null){
            throw new ItemValidationException("Menu id, name and price are all mandatory fields");
        }
        Optional<Menu> menuOptional = menuRepository.findById(request.getMenuId());
        if(menuOptional.isEmpty()){
            throw new MenuDoesNotExistException("Menu doesn't exist with this Id");
        }
        final var menu=menuOptional.get();
        Item item = ItemMapper.toEntity(request,menu);
        item = itemRepository.save(item);
        return ItemMapper.toResponse(item);
    }

    public List<ItemResponse> getItemsByMenu(Long menuId){
        Optional<Menu> menuOptional = menuRepository.findById(menuId);
        if(menuOptional.isEmpty()){
            throw new MenuDoesNotExistException("Menu doesn't exist with this Id");
        }
        List<Item> items = itemRepository.findByMenuId(menuId);
        List<ItemResponse> response = new ArrayList<>();
        for(var i=0;i<items.size();i++){
            response.add(ItemMapper.toResponse(items.get(i)));
        }
        return response;
    }

    public DeleteItemResponse deleteItem(Long itemId){
        Optional<Item> optionalItem = itemRepository.findById(itemId);

        if(optionalItem.isEmpty()){
            throw new ItemDoesNotExistException("Item does not exist with this Id");
        }
        itemRepository.deleteById(itemId);
        DeleteItemResponse response = new DeleteItemResponse();
        response.setMessage("Item deleted successfully!");
        return response;
    }
}
