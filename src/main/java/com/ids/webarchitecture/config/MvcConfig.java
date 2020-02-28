package com.ids.webarchitecture.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("index.html");
        registry.addViewController("/").setViewName("index.html");
        registry.addViewController("/architecture").setViewName("architecture.html");
        registry.addViewController("/login").setViewName("login.html");
    }

}
