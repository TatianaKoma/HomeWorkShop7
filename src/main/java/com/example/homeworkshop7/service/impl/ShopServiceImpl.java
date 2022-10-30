package com.example.homeworkshop7.service.impl;

import com.example.homeworkshop7.exception.NotFoundException;
import com.example.homeworkshop7.model.Shop;
import com.example.homeworkshop7.repository.ShopRepository;
import com.example.homeworkshop7.service.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.homeworkshop7.ResponseMessages.SHOP_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;

    @Override
    public Shop createShop(Shop shop) {
        log.debug("Get shop {}", shop);
        return shopRepository.save(shop);
    }

    @Override
    public List<Shop> getShops() {
        return shopRepository.findAll();
    }

    @Override
    public void deleteShopById(Integer id) {
        Shop shopForDelete = shopRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Unable to delete. Shop with id {} was not found", id);
                    return new NotFoundException(String.format(SHOP_NOT_FOUND, id));
                });
        shopRepository.delete(shopForDelete);
    }
}
