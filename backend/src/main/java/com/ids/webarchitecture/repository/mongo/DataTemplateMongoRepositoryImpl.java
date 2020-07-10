package com.ids.webarchitecture.repository.mongo;

import com.ids.webarchitecture.model.mongo.DataTemplateMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.regex.Pattern;

public class DataTemplateMongoRepositoryImpl implements DataTemplateMongoRepositoryCustom {

    final private static String FIELD_TEXT = "text";
    final private static String FIELD_NAME = "name";
    final private static String FIELD_AUTHOR_NAME = "author.name";

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<DataTemplateMongo> findAllByQuery(String text) {
        Pattern pattern = Pattern.compile(text, Pattern.CASE_INSENSITIVE);

        Criteria orCriteria = new Criteria();
        orCriteria.orOperator(
                Criteria.where(FIELD_TEXT).regex(pattern),
                Criteria.where(FIELD_NAME).regex(pattern),
                Criteria.where(FIELD_AUTHOR_NAME).regex(pattern)
        );

        Query query = new Query();
        query.addCriteria(orCriteria);
        return mongoTemplate.find(query, DataTemplateMongo.class);
    }
}
