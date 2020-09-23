package com.ids.webarchitecture.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Profile("test")
//@EnableTransactionManagement
@PropertySource("classpath:/application.test.properties")
@PropertySource(value = "classpath:/application.test.local.properties", ignoreResourceNotFound = true)
@ComponentScan({
        "com.ids.webarchitecture.service",
        //"com.ids.webarchitecture.repository",
        //"com.ids.webarchitecture.repository.mongo"
})
//@EnableAutoConfiguration
//@EnableJpaRepositories(basePackages = "com.ids.webarchitecture.repository")
//@EnableMongoRepositories(basePackages = "com.ids.webarchitecture.repository.mongo")
public class AppTestConfig {

}
