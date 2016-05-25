package com.webmarket.application.config.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.webmarket.**.service"})
public class AppConfig {
}
