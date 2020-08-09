package com.ids.webarchitecture.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@PropertySource(value = "classpath:/application.local.properties", ignoreResourceNotFound = true)
public class AppConfig {
}
