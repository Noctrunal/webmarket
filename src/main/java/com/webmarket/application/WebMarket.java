package com.webmarket.application;

import com.webmarket.application.model.Role;
import com.webmarket.application.model.User;
import com.webmarket.application.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class WebMarket {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebMarket.class, args);
    }

    @Bean
    public CommandLineRunner runner(UserRepository repository) throws Exception {
        return args -> {
            repository.deleteAll();

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            User admin = new User();
            admin.setEmail("admin@gmail.com");
            admin.setPassword(encoder.encode("admin"));
            admin.setRoles(Role.ROLE_ADMIN, Role.ROLE_USER);

            User user = new User();
            user.setEmail("user@gmail.com");
            user.setPassword(encoder.encode("user"));
            user.setRoles(Role.ROLE_USER);

            repository.save(admin);

            repository.save(user);
        };
    }
}
