package com.example.homeworkshop7.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "products")
    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Product> products = new ArrayList<>();
}
