package com.ids.webarchitecture.repository.mongo;

import com.ids.webarchitecture.model.mongo.DataTemplateMongo;

import java.util.List;

public interface DataTemplateMongoRepositoryCustom {
    List<DataTemplateMongo> findAllByQuery(String query);
}
