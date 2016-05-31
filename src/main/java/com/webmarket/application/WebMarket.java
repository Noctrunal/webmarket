package com.webmarket.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
public class WebMarket {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebMarket.class, args);
    }

    @Configuration
    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    protected static class WebMarketSecurity extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.requiresChannel().anyRequest().requiresSecure();
            http.formLogin().loginPage("/login").failureUrl("/login?error").permitAll().and().logout().permitAll().and().rememberMe().rememberMeParameter("remember-me");
            http.authorizeRequests().antMatchers("/manage/**").hasRole("ADMIN");
            http.csrf().disable();
        }

        @Override
        public void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication().withUser("admin@gmail.com").password("admin")
                    .roles("ADMIN", "USER").and().withUser("user@gmail.com").password("user")
                    .roles("USER");
        }
    }
}
