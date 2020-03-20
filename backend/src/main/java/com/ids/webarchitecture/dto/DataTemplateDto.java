package com.ids.webarchitecture.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataTemplateDto extends NamedDto {
    private String text;
    private ProductAuthorDto author;
}
