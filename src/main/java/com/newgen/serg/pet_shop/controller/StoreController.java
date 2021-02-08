package com.newgen.serg.pet_shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.newgen.serg.pet_shop.entity.*;
import com.newgen.serg.pet_shop.exception.DataIsNotValidException;
import com.newgen.serg.pet_shop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class StoreController {
    private final ProductService productService;
    private final ObjectMapper objectMapper;

    @PostMapping("/store")
    public void getAllProductsByType(@RequestBody List<Map<String, Object>> products) throws DataIsNotValidException {
        for(Map<String, Object> prod : products) {
            productService.save(mapProduct(prod));
        }
    }

    @PutMapping("/store/{id}")
    public void getAllProductsByType(@PathVariable Long id, @RequestBody Map<String, Object> product) throws DataIsNotValidException {
        Product prod = mapProduct(product);
        prod.setId(id);
        productService.save(prod);
    }

    @DeleteMapping("/store/{id}")
    public void getAllProductsByType(@PathVariable Long id) {
        productService.delete(id);
    }

    private Product mapProduct(Map<String, Object> product) throws DataIsNotValidException {
        product.remove("id");
        try {
            switch (product.get("type").toString().toUpperCase()) {
                case "PET": {
                    return objectMapper.convertValue(product, Pet.class);
                }
                case "FOOD": {
                    return objectMapper.convertValue(product, Food.class);
                }
                case "STUFF": {
                    return objectMapper.convertValue(product, Stuff.class);
                }
                case "PET_LITTER": {
                    return objectMapper.convertValue(product, PetLitter.class);
                }
                default: {
                    return objectMapper.convertValue(product, Product.class);
                }
            }
        } catch (Exception ex) {
            throw new DataIsNotValidException(ex.getMessage());
        }
    }
}
