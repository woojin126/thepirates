package com.thepirates.subject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;


@Data
@NoArgsConstructor
public class ProductResponseDto {
    private String name;
    private String description;
    private String price;

    @Builder
    public ProductResponseDto(String name, String description, Integer price) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        this.name = name;
        this.description = description;
        this.price = decimalFormat.format(price) + " ~ "; //금액 String 콤마 형식 변환
    }
}
