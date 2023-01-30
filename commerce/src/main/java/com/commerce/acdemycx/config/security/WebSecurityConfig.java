package com.commerce.acdemycx.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class WebSecurityConfig {



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .httpBasic()
                .and()
                .authorizeHttpRequests()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();

        return http.build();
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsService(){
//        UserDetails user = User.withUsername("ana").password(passwordEncoder().encode("senha123")).roles().build();
//        return new InMemoryUserDetailsManager(user);
//    }



    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
