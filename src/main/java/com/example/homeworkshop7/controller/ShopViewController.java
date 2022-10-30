package com.example.homeworkshop7.controller;

import com.example.homeworkshop7.dto.ShopCreationDto;
import com.example.homeworkshop7.dto.ShopDto;
import com.example.homeworkshop7.mapper.ShopMapper;
import com.example.homeworkshop7.model.Shop;
import com.example.homeworkshop7.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@Validated
public class ShopViewController {

    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopMapper mapper;

    @RequestMapping("/getShops")
    @PreAuthorize("isAuthenticated()")
    public String getAllShops(Model model) {
        List<ShopDto> shopDto = shopService.getShops().stream()
                .map(mapper::toShopDTO)
                .collect(Collectors.toList());
        model.addAttribute("shop", shopDto);
        return "getShops";
    }

    @RequestMapping("/addNewShop")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addNewShop(Model model) {
        model.addAttribute("shop", new ShopDto());
        return "shopInfo";
    }

    @RequestMapping("/saveShop")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String saveShop(@Valid @ModelAttribute("shop") ShopCreationDto shopCreationDto,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "shopInfo";
        } else {
            Shop shop = mapper.toShop(shopCreationDto);
            shopService.createShop(shop);
            log.info("Shop was created");
            return "redirect:/getShops";
        }
    }

    @RequestMapping("/deleteShop")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteShop(@RequestParam("shopId") int id) {
        shopService.deleteShopById(id);
        log.info("Shop with id {} was deleted", id);
        return "redirect:/getShops";
    }
}
