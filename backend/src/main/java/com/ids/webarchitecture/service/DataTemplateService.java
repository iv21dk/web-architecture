package com.ids.webarchitecture.service;

import com.ids.webarchitecture.dto.DataTemplateDto;
import com.ids.webarchitecture.dto.ProductAuthorDto;
import com.ids.webarchitecture.model.mongo.DataTemplateMongo;

import java.util.List;

public interface DataTemplateService {

    int MIN_TEMPLATE_TEXT_LENGTH = 100;

    List<DataTemplateDto> getAllTemplates(String query);
    DataTemplateMongo createDataTemplate(DataTemplateDto dataTemplateDto);
    DataTemplateDto createDataTemplateAndConvert(DataTemplateDto dataTemplateDto);
    List<ProductAuthorDto> getAllAuthors();
    void updateDataTemplate(String templateId, DataTemplateDto dataTemplateDto);
}
