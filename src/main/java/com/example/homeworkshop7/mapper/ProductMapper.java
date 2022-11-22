package com.example.homeworkshop7.mapper;

import com.example.homeworkshop7.dto.ProductCreationDto;
import com.example.homeworkshop7.dto.ProductDto;
import com.example.homeworkshop7.exception.NotFoundException;
import com.example.homeworkshop7.model.Product;
import com.example.homeworkshop7.model.Shop;
import com.example.homeworkshop7.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.example.homeworkshop7.ResponseMessages.SHOP_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class ProductMapper {
    private final ShopRepository shopRepository;

    public Product toProduct(ProductCreationDto productCreationDTO) {
        Product product = new Product();
        product.setName(productCreationDTO.getName());
        product.setPrice(productCreationDTO.getPriceUah());
        Shop shop = shopRepository.findById(productCreationDTO.getShopId())
                .orElseThrow(() -> new NotFoundException(String.format(SHOP_NOT_FOUND,
                        productCreationDTO.getShopId())));
        product.setShop(shop);
        return product;
    }

    public Product toProduct(ProductDto productDTO) {
        Product product = new Product();
        product.setId(productDTO.getProductId());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPriceUah());
        Shop shop = shopRepository.findById(productDTO.getShopId())
                .orElseThrow(() -> new NotFoundException(String.format(SHOP_NOT_FOUND,
                        productDTO.getShopId())));
        product.setShop(shop);
        return product;
    }

    public ProductDto toProductDTO(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setProductId(product.getId());
        productDto.setName(product.getName());
        productDto.setPriceUah(product.getPrice());
        productDto.setShopId(product.getShop().getId());
        return productDto;
    }
}
