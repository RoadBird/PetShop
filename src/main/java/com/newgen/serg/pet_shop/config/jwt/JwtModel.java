package com.newgen.serg.pet_shop.config.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class JwtModel {
    @Value("${security.jwt.uri:/auth/**}")
    private String Uri;

    @Value("${security.jwt.header:Authorization}")
    private String header;

    @Value("${security.jwt.prefix:Bearer}")
    private String prefix;

    @Value("${security.jwt.expiration:#{7*24*60*60}}")
    private int expiration;

    @Value("${security.jwt.secret:6d590088effd}")
    private String secret;
}
