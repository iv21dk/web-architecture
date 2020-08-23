package com.ids.webarchitecture.controller;

import com.ids.webarchitecture.dto.DataTemplateDto;
import com.ids.webarchitecture.dto.ProductAuthorDto;
import com.ids.webarchitecture.service.DataTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DataTemplateController {

    @Autowired
    private DataTemplateService dataTemplateService;

    @GetMapping("/api/data-templates")
    @ResponseStatus(HttpStatus.OK)
    public List<DataTemplateDto> getAllTemplates(
            @RequestParam(name = "query", required = false) String query) {
        return dataTemplateService.getAllTemplates(query);
    }

    @PostMapping("/api/data-templates")
    @ResponseStatus(HttpStatus.CREATED)
    public DataTemplateDto createDataTemplate(@RequestBody DataTemplateDto dataTemplateDto) {
        return dataTemplateService.createDataTemplateAndConvert(dataTemplateDto);
    }

    @PutMapping("/api/data-templates/{template-id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateDataTemplate(@PathVariable("template-id") String templateId, @RequestBody DataTemplateDto dataTemplateDto) {
        dataTemplateService.updateDataTemplate(templateId, dataTemplateDto);
    }

    @GetMapping("/api/data-templates/authors")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductAuthorDto> getAllAuthors() {
        return dataTemplateService.getAllAuthors();
    }

}
