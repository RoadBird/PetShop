package com.newgen.serg.pet_shop.controller;

import com.newgen.serg.pet_shop.entity.Product;
import com.newgen.serg.pet_shop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CatalogController {
    private final ProductService productService;

    @GetMapping({"/", "/catalog"})
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/catalog/{type}")
    public List<Product> getAllProductsByType(@PathVariable String type) {
        return productService.findAllByType(type);
    }
}
