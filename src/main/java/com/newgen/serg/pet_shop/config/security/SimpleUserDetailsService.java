package com.newgen.serg.pet_shop.config.security;

import com.newgen.serg.pet_shop.entity.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class SimpleUserDetailsService implements UserDetailsService {
    @Value("${security.admin.username:admin}")
    private String adminUsername;
    @Value("${security.admin.password:password}")
    private String adminPassword;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        if (username.equalsIgnoreCase(adminUsername)) {
            return new User(adminUsername, passwordEncoder.encode(adminPassword), getAuthorities(UserRole.ROLE_ADMIN));
        } else {
            throw new UsernameNotFoundException(username + " not found");
        }
    }

    private Collection<? extends GrantedAuthority> getAuthorities(UserRole role) {
        List<SimpleGrantedAuthority> auths = new ArrayList<>();
        auths.add(new SimpleGrantedAuthority(role.name()));
        return auths;
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
