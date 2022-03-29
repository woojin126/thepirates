package com.thepirates.subject.dto;

import lombok.*;

import java.util.List;


@Data
public class ProductDetailResponseDto {

    private String name;
    private String description;
    private String delivery;
    private List<OptionResponseDto> options;

    public ProductDetailResponseDto(String name, String description, String delivery) {
        this.name = name;
        this.description = description;
        this.delivery = delivery;
    }

    public void saveOptions(List<OptionResponseDto> options) {
        this.options = options;
    }
}
