package com.newgen.serg.pet_shop.config;

import com.newgen.serg.pet_shop.config.jwt.JwtModel;
import com.newgen.serg.pet_shop.config.jwt.JwtTokenAuthenticationFilter;
import com.newgen.serg.pet_shop.config.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.BASIC_AUTH_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final JwtModel jwtModel;

    public SecurityConfig(@Qualifier("appUserDetailsService") UserDetailsService userDetailsService, JwtModel jwtModel) {
        this.userDetailsService = userDetailsService;
        this.jwtModel = jwtModel;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .addFilterAfter(new JwtTokenAuthenticationFilter(jwtModel), UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtModel), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/**", "/catalog*", "/catalog/**").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers(HttpMethod.POST, jwtModel.getUri()).permitAll()
//                .antMatchers("/store*", "/store/**").hasAnyRole("ROLE_USER", "ROLE_ADMIN")
                .anyRequest().authenticated()
                .and().csrf().disable().cors();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Collections.singletonList("*"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() {
        try {
            return super.authenticationManagerBean();
        } catch (Exception e) {
            return null;
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
