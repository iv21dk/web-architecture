package com.ids.webarchitecture.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ids.webarchitecture.dto.DataTemplateDto;
import com.ids.webarchitecture.dto.ProductAuthorDto;
import com.ids.webarchitecture.excption.RequestParameterException;
import com.ids.webarchitecture.model.mongo.DataTemplate;
import com.ids.webarchitecture.model.mongo.ProductAuthor;
import com.ids.webarchitecture.repository.mongo.DataTemplateMongoRepository;
import com.ids.webarchitecture.repository.mongo.ProductAuthorMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.ids.webarchitecture.utils.ServiceUtils.checkFound;

@Service
public class DataTemplateService {

    @Autowired
    private DataTemplateMongoRepository dataTemplateMongoRepository;
    @Autowired
    private ProductAuthorMongoRepository productAuthorMongoRepository;

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

    private ProductAuthorDto productAuthorBoToDto(ProductAuthor productAuthor) {
        return mapper.convertValue(productAuthor, ProductAuthorDto.class);
    }

    private ProductAuthor productAuthorDtoToBo(ProductAuthorDto productAuthorDto) {
        return mapper.convertValue(productAuthorDto, ProductAuthor.class);
    }

    public DataTemplate createDataTemplate(DataTemplateDto dataTemplateDto) {
        validate(dataTemplateDto);
        DataTemplate dataTemplate = dataTemplateDtoToBo(dataTemplateDto);
        dataTemplate.setId(null);
        resolveProductAuthor(dataTemplate);
        return dataTemplateMongoRepository.save(dataTemplate);
    }

    private void resolveProductAuthor(DataTemplate dataTemplate) {
        if (dataTemplate.getAuthor() != null) {
            if (dataTemplate.getAuthor().getId() == null || dataTemplate.getAuthor().getId().isBlank()) {
                ProductAuthor productAuthor = productAuthorMongoRepository.save(dataTemplate.getAuthor());
                dataTemplate.setAuthor(productAuthor);
            } else {
                dataTemplate.setAuthor(
                        checkFound(productAuthorMongoRepository.findById(dataTemplate.getAuthor().getId())));
            }
        }
    }

    public DataTemplateDto createDataTemplateAndConvert(DataTemplateDto dataTemplateDto) {
        return dataTemplateBoToDto(createDataTemplate(dataTemplateDto));
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
        return productAuthorMongoRepository.findAll().stream()
                .map(i->productAuthorBoToDto(i)).collect(Collectors.toList());
    }
}
