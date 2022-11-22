package com.example.homeworkshop7.mapper;

import com.example.homeworkshop7.dto.CartCreationDto;
import com.example.homeworkshop7.dto.CartDto;
import com.example.homeworkshop7.exception.NotFoundException;
import com.example.homeworkshop7.model.Cart;
import com.example.homeworkshop7.model.Person;
import com.example.homeworkshop7.model.Product;
import com.example.homeworkshop7.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.homeworkshop7.ResponseMessages.PERSON_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class CartMapper {
    private final PersonRepository personRepository;

    public Cart toCart(CartCreationDto cartCreationDto) {
        Integer personId = cartCreationDto.getPersonId();
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new NotFoundException(String.format(PERSON_NOT_FOUND, personId)));
        Cart cart = new Cart();
        cart.setPerson(person);
        return cart;
    }

    public CartDto toCartDTO(Cart cart) {
        List<Integer> productsId = cart.getProducts().stream()
                .map(Product::getId)
                .collect(Collectors.toList());
        CartDto cartDto = new CartDto();
        cartDto.setId(cart.getId());
        cartDto.setPersonId(cart.getPerson().getId());
        cartDto.setSumUah(cart.getSum());
        cartDto.setProductsId(productsId);
        return cartDto;
    }
}
