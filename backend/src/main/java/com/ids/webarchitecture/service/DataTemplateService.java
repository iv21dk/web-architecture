package com.ids.webarchitecture.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ids.webarchitecture.dto.DataTemplateDto;
import com.ids.webarchitecture.dto.ProductAuthorDto;
import com.ids.webarchitecture.exception.RequestParameterException;
import com.ids.webarchitecture.model.mongo.DataTemplateMongo;
import com.ids.webarchitecture.model.mongo.ProductAuthorTemplateMongo;
import com.ids.webarchitecture.repository.mongo.DataTemplateMongoRepository;
import com.ids.webarchitecture.repository.mongo.ProductAuthorTemplateMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

import static com.ids.webarchitecture.utils.ServiceUtils.checkFound;

@Service
public class DataTemplateService {

    public static final int MIN_TEMPLATE_TEXT_LENGTH = 100;

    @Autowired
    private DataTemplateMongoRepository dataTemplateMongoRepository;
    @Autowired
    private ProductAuthorTemplateMongoRepository authorTemplateMongoRepository;

    private ObjectMapper mapper = new ObjectMapper();

    public List<DataTemplateDto> getAllTemplates(String query) {
        List<DataTemplateMongo> templates;
        if (StringUtils.isEmpty(query)) {
            templates = dataTemplateMongoRepository.findAll();
        } else {
            templates = dataTemplateMongoRepository.findAllByQuery(query);
        }
        return templates.stream().map(i->dataTemplateBoToDto(i)).collect(Collectors.toList());
    }

    private DataTemplateDto dataTemplateBoToDto(DataTemplateMongo dataTemplate) {
        return mapper.convertValue(dataTemplate, DataTemplateDto.class);
    }

    private DataTemplateMongo dataTemplateDtoToBo(DataTemplateDto dataTemplateDto) {
        return mapper.convertValue(dataTemplateDto, DataTemplateMongo.class);
    }

    private ProductAuthorDto productAuthorBoToDto(ProductAuthorTemplateMongo productAuthor) {
        return mapper.convertValue(productAuthor, ProductAuthorDto.class);
    }

    private ProductAuthorTemplateMongo productAuthorDtoToBo(ProductAuthorDto productAuthorDto) {
        return mapper.convertValue(productAuthorDto, ProductAuthorTemplateMongo.class);
    }

    public DataTemplateMongo createDataTemplate(DataTemplateDto dataTemplateDto) {
        validate(dataTemplateDto);
        validate(dataTemplateDto.getAuthor());
        DataTemplateMongo dataTemplate = dataTemplateDtoToBo(dataTemplateDto);
        dataTemplate.setId(null);
        resolveProductAuthor(dataTemplate);
        return dataTemplateMongoRepository.save(dataTemplate);
    }

    private void resolveProductAuthor(DataTemplateMongo dataTemplate) {
        if (dataTemplate.getAuthor().getId() == null || dataTemplate.getAuthor().getId().isBlank()) {
            ProductAuthorTemplateMongo productAuthor = authorTemplateMongoRepository.save(dataTemplate.getAuthor());
            dataTemplate.setAuthor(productAuthor);
        } else {
            dataTemplate.setAuthor(
                    checkFound(authorTemplateMongoRepository.findById(dataTemplate.getAuthor().getId())));
        }
    }

    public DataTemplateDto createDataTemplateAndConvert(DataTemplateDto dataTemplateDto) {
        return dataTemplateBoToDto(createDataTemplate(dataTemplateDto));
    }

    private void validate(DataTemplateDto dataTemplateDto) {
        if (dataTemplateDto.getName() == null || dataTemplateDto.getName().isBlank()) {
            throw new RequestParameterException("Field 'name' of the template should be defined");
        }
        if (dataTemplateDto.getText() == null || dataTemplateDto.getText().isBlank()) {
            throw new RequestParameterException("Field 'text' of the template should be defined");
        }
        if (dataTemplateDto.getText().length() < MIN_TEMPLATE_TEXT_LENGTH) {
            throw new RequestParameterException("Text of the template should have minimum length: "
                    + MIN_TEMPLATE_TEXT_LENGTH);
        }
        if (dataTemplateDto.getAuthor() == null) {
            throw new RequestParameterException("Field 'author' of the template should be defined");
        }
    }

    private void validate(ProductAuthorDto productAuthorDto) {
        if ((productAuthorDto.getId() == null || productAuthorDto.getId().isBlank())
                && (productAuthorDto.getName() == null || productAuthorDto.getName().isBlank())) {
            throw new RequestParameterException("Field 'name' of the author should be defined");
        }
    }

    public List<ProductAuthorDto> getAllAuthors() {
        return authorTemplateMongoRepository.findAll().stream()
                .map(i->productAuthorBoToDto(i)).collect(Collectors.toList());
    }

    public void updateDataTemplate(String templateId, DataTemplateDto dataTemplateDto) {
        validate(dataTemplateDto);
        if (dataTemplateDto.getAuthor() != null) {
            validate(dataTemplateDto.getAuthor());
        }
        checkFound(dataTemplateMongoRepository.findById(templateId));
        DataTemplateMongo dataTemplate = dataTemplateDtoToBo(dataTemplateDto);
        dataTemplate.setId(templateId);
        resolveProductAuthor(dataTemplate);
        dataTemplateMongoRepository.save(dataTemplate);
    }
}
