package com.example.homeworkshop7.controller;

import com.example.homeworkshop7.dto.ProductCreationDto;
import com.example.homeworkshop7.dto.ProductDto;
import com.example.homeworkshop7.facade.ProductFacade;
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
public class ProductViewController {

    private final ProductFacade productFacade;

    @RequestMapping("/getProducts")
    @PreAuthorize("isAuthenticated()")
    public String getAllProducts(Model model) {
        List<ProductDto> productsDTO = productFacade.getProducts();
        model.addAttribute("product", productsDTO);
        return "getProducts";
    }

    @RequestMapping("/addNewProduct")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addNewProduct(Model model) {
        model.addAttribute("product", new ProductDto());
        return "productInfo";
    }

    @RequestMapping("/saveProduct")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String saveProduct(@Valid @ModelAttribute("product") ProductCreationDto productCreationDto,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "productInfo";
        } else {
            productFacade.createProduct(productCreationDto);
            log.info("Product was created");
            return "redirect:/getProducts";
        }
    }

    @RequestMapping("/updateProduct")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String updateProduct(@RequestParam("productId") int id, Model model) {
        ProductDto productDto = productFacade.getProductById(id);
        model.addAttribute("updatedProduct", productDto);
        return "productUpdate";
    }

    @RequestMapping("/saveUpdatedProduct")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String saveUpdatedProduct(@Valid @ModelAttribute("updatedProduct") ProductDto productDto,
                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "productUpdate";
        } else {
            productFacade.updateProductById(productDto.getProductId(), productDto);
            log.info("Product with id {} was updated", productDto.getProductId());
            return "redirect:/getProducts";
        }
    }

    @RequestMapping("/deleteProduct")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteProduct(@RequestParam("productId") int id) {
        productFacade.deleteProductById(id);
        log.info("Product with id {} was deleted", id);
        return "redirect:/getProducts";
    }
}
