package com.ids.webarchitecture.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ids.webarchitecture.dto.DataTemplateDto;
import com.ids.webarchitecture.dto.ProductAuthorDto;
import com.ids.webarchitecture.excption.RequestParameterException;
import com.ids.webarchitecture.model.mongo.DataTemplate;
import com.ids.webarchitecture.repository.mongo.DataTemplateMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataTemplateService {

    @Autowired
    private DataTemplateMongoRepository dataTemplateMongoRepository;

    private ObjectMapper mapper = new ObjectMapper();

    public List<DataTemplateDto> getAllTemplates() {
        return dataTemplateMongoRepository.findAll().stream()
                .map(i->dataTemplateBoToDto(i)).collect(Collectors.toList());
    }

    private DataTemplateDto dataTemplateBoToDto(DataTemplate dataTemplate) {
        return mapper.convertValue(dataTemplate, DataTemplateDto.class);
    }

    private DataTemplate dataTemplateDtoToBo(DataTemplateDto dataTemplateDto) {
        return mapper.convertValue(dataTemplateDto, DataTemplate.class);
    }

    public void createDataTemplate(DataTemplateDto dataTemplateDto) {
        validate(dataTemplateDto);
        dataTemplateDto.setId(null);
        dataTemplateMongoRepository.save(dataTemplateDtoToBo(dataTemplateDto));
    }

    private void validate(DataTemplateDto dataTemplateDto) {
        if (dataTemplateDto.getName() == null || dataTemplateDto.getName().isBlank()) {
            throw new RequestParameterException("Field 'name' should be defined");
        }
        if (dataTemplateDto.getText() == null || dataTemplateDto.getText().isBlank()) {
            throw new RequestParameterException("Field 'text' should be defined");
        }
    }

    public List<ProductAuthorDto> getAllAuthors() {
        ProductAuthorDto author1 = new ProductAuthorDto();
        author1.setId("123");
        author1.setName("Пушкин");

        ProductAuthorDto author2 = new ProductAuthorDto();
        author2.setId("124");
        author2.setName("Лермонтов");

        List<ProductAuthorDto> result = new ArrayList<>();
        result.add(author1);
        result.add(author2);
        return result;
    }
}
