package com.newgen.serg.pet_shop.repository;

import com.newgen.serg.pet_shop.entity.ShopUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<ShopUser, Long> {
    Optional<ShopUser> findByEmailIgnoreCaseAndPassword(String email, String password);
    Optional<ShopUser> findByEmailIgnoreCase(String email);
}
