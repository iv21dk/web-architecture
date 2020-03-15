package com.ids.webarchitecture.controller;

import com.ids.webarchitecture.dto.DataTemplateDto;
import com.ids.webarchitecture.dto.ProductAuthorDto;
import com.ids.webarchitecture.service.DataTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public void createDataTemplate(@RequestBody DataTemplateDto dataTemplateDto) {
        dataTemplateService.createDataTemplate(dataTemplateDto);
    }

    @GetMapping("/api/data-templates/authors")
    public List<ProductAuthorDto> getAllAuthors() {
        return dataTemplateService.getAllAuthors();
    }

}
