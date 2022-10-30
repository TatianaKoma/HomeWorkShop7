package com.example.homeworkshop7.controller;

import com.example.homeworkshop7.dto.CartCreationDto;
import com.example.homeworkshop7.dto.CartDto;
import com.example.homeworkshop7.dto.CartUpdateDto;
import com.example.homeworkshop7.dto.ProductDto;
import com.example.homeworkshop7.mapper.CartMapper;
import com.example.homeworkshop7.mapper.ProductMapper;
import com.example.homeworkshop7.model.Cart;
import com.example.homeworkshop7.model.Product;
import com.example.homeworkshop7.service.CartService;
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
public class CartViewController {

    @Autowired
    private CartService cartService;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;

    @RequestMapping("/getCarts")
    @PreAuthorize("isAuthenticated()")
    public String getAllCarts(Model model) {
        List<Cart> carts = cartService.getCarts();
        List<CartDto> cartDto = carts.stream().map(cartMapper::toCartDTO).collect(Collectors.toList());
        model.addAttribute("cart", cartDto);
        return "getCarts";
    }

    @RequestMapping("/addNewCart")
    @PreAuthorize("isAuthenticated()")
    public String addNewCart(Model model) {
        model.addAttribute("cart", new CartDto());
        return "cartInfo";
    }

    @RequestMapping("/saveCart")
    @PreAuthorize("isAuthenticated()")
    public String saveCart(@Valid @ModelAttribute("cart") CartCreationDto cartCreationDto,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "cartInfo";
        } else {
            Cart cart = cartMapper.toCart(cartCreationDto);
            cartService.createCart(cart);
            log.info("New cart was added");
            return "redirect:/getCarts";
        }
    }

    @RequestMapping("/addProductToCart")
    @PreAuthorize("isAuthenticated()")
    public String updateCart(@RequestParam("cartId") int id, Model model) {
        Cart cart = cartService.getCartById(id);
        CartDto cartDto = cartMapper.toCartDTO(cart);
        model.addAttribute("updatedCart", cartDto);
        log.info("Cart with id {} was updated", id);
        return "cartUpdate";
    }

    @RequestMapping("/saveProductToCart")
    @PreAuthorize("isAuthenticated()")
    public String saveProductToCart(@Valid @ModelAttribute("updatedCart") CartUpdateDto cartUpdateDto,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "cartUpdate";
        } else {
            cartService.addProductToCartById(cartUpdateDto.getId(), cartUpdateDto.getProductsId());
            log.info("Product with id {} was added to cart", cartUpdateDto.getProductsId());
            return "redirect:/getCarts";
        }
    }

    @RequestMapping("/listProducts")
    @PreAuthorize("isAuthenticated()")
    public String getAllProductsFromCart(@RequestParam("cartId") int id, Model model) {
        List<Product> productList = cartService.getListProductsByCartId(id);
        List<ProductDto> productDtos = productList.stream().map(productMapper::toProductDTO).collect(Collectors.toList());
        model.addAttribute("listProducts", productDtos);
        return "getListProducts";
    }

    @RequestMapping("/deleteCart")
    @PreAuthorize("isAuthenticated()")
    public String deleteCart(@RequestParam("cartId") int id) {
        cartService.deleteCartById(id);
        log.info("Cart with id {} was deleted", id);
        return "redirect:/getCarts";
    }
}
