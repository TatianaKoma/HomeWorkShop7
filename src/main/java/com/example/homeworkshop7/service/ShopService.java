package com.example.homeworkshop7.service;

import com.example.homeworkshop7.model.Shop;

import java.util.List;

public interface ShopService {
    Shop createShop(Shop shop);

    List<Shop> getShops();

    void deleteShopById(Integer id);
}
