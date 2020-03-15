package com.ids.webarchitecture.controller;

import com.ids.webarchitecture.dto.DataTemplateDto;
import com.ids.webarchitecture.dto.ProductAuthorDto;
import com.ids.webarchitecture.service.DataTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DataTemplateController {

    @Autowired
    private DataTemplateService dataTemplateService;

    @GetMapping("/api/data-templates")
    public List<DataTemplateDto> getAllTemplates() {
        return dataTemplateService.getAllTemplates();
    }

    @PostMapping("/api/data-templates")
    @ResponseStatus(HttpStatus.CREATED)
    public DataTemplateDto createDataTemplate(@RequestBody DataTemplateDto dataTemplateDto) {
        return dataTemplateService.createDataTemplateAndConvert(dataTemplateDto);
    }

    @GetMapping("/api/data-templates/authors")
    public List<ProductAuthorDto> getAllAuthors() {
        return dataTemplateService.getAllAuthors();
    }

}
