package com.academiacx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AcademiacxApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcademiacxApplication.class, args);
        // System.out.println(new BCryptPasswordEncoder().encode("senha123"));
    }

}
