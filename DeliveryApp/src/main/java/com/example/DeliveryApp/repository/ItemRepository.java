package com.example.DeliveryApp.repository;

import com.example.DeliveryApp.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByMenuId(Long menuId);


    void deleteByMenuId(Long menuId);
}
