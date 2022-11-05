package com.example.homeworkshop7.controller;

import com.example.homeworkshop7.dto.ShopCreationDto;
import com.example.homeworkshop7.dto.ShopDto;
import com.example.homeworkshop7.facade.ShopFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Controller
@Validated
@RequiredArgsConstructor
public class ShopViewController {

    private final ShopFacade shopFacade;

    @RequestMapping("/getShops")
    @PreAuthorize("isAuthenticated()")
    public String getAllShops(Model model) {
        List<ShopDto> shopDto = shopFacade.getShops();
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
            shopFacade.createShop(shopCreationDto);
            log.info("Shop was created");
            return "redirect:/getShops";
        }
    }

    @RequestMapping("/deleteShop")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteShop(@RequestParam("shopId") int id) {
        shopFacade.deleteShopById(id);
        log.info("Shop with id {} was deleted", id);
        return "redirect:/getShops";
    }
}
