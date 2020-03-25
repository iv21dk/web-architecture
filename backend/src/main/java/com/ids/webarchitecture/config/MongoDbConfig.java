package com.ids.webarchitecture.config;

import org.hibernate.metamodel.model.domain.DomainType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.config.MongoConfigurationSupport;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.IndexOperations;
import org.springframework.data.mongodb.core.index.IndexResolver;
import org.springframework.data.mongodb.core.index.MongoPersistentEntityIndexResolver;

//@Configuration
public class MongoDbConfig {//extends MongoConfigurationSupport {

//    @Value("${spring.data.mongodb.database}")
//    String databaseName;
//
//    @Override
//    protected String getDatabaseName() {
//        return this.databaseName;
//    }
//
//    @Override
//    protected boolean autoIndexCreation() {
//        return true;
//    }

    //    private final MongoTemplate mongoTemplate;
//    private final MongoConverter mongoConverter;
//
//    @EventListener(ApplicationReadyEvent.class)
//    public void initIndicesAfterStartup() {
//
//        IndexOperations indexOps = mongoTemplate.indexOps(DomainType.class);
//
//        IndexResolver resolver = new MongoPersistentEntityIndexResolver(mongoMappingContext);
//        resolver.resolveIndexFor(DomainType.class).forEach(indexOps::ensureIndex);
//    }
}
