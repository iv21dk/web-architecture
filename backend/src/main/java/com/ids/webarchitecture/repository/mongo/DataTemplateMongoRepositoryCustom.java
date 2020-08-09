package com.ids.webarchitecture.repository.mongo;

import com.ids.webarchitecture.model.mongo.DataTemplateMongo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataTemplateMongoRepositoryCustom {
    List<DataTemplateMongo> findAllByQuery(String query);
}
