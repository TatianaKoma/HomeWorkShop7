package com.example.homeworkshop7.repository;

import com.example.homeworkshop7.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
}
