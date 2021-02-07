package com.newgen.serg.pet_shop.repository;

import com.newgen.serg.pet_shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByTypeIgnoreCase(String type);
}
