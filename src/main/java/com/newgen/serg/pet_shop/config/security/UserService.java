package com.newgen.serg.pet_shop.config.security;

import com.newgen.serg.pet_shop.entity.ShopUser;
import com.newgen.serg.pet_shop.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void create(ShopUser e) {
        userRepository.save(e);
    }

    public Optional<ShopUser> findById(Long id) {
        return userRepository.findById(id);
    }

    public List<ShopUser> findAll() {
        return userRepository.findAll();
    }

    public ShopUser update(ShopUser e) {
        return userRepository.save(e);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<ShopUser> getUserByUserEmailAndPassword(String email, String password) {
        return userRepository.findByEmailIgnoreCaseAndPassword(email, password);
    }

    public Optional<ShopUser> getUserByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }
}
