package com.newgen.serg.pet_shop.config.security;

import com.newgen.serg.pet_shop.entity.ShopUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AppUserDetailsService implements UserDetailsService {
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        ShopUser shopUser = userService.getUserByEmail(username).orElseThrow(() ->  new UsernameNotFoundException(username + " not found"));
        return new org.springframework.security.core.userdetails.User(shopUser.getEmail(),
                shopUser.getPassword(), getAuthorities(shopUser));
    }

    public Collection<? extends GrantedAuthority> getAuthorities(ShopUser shopUser) {
        List<SimpleGrantedAuthority> auths = new ArrayList<>();
        auths.add(new SimpleGrantedAuthority(shopUser.getRole().name()));
        return auths;
    }

    public UserService getUserService() {
        return userService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}