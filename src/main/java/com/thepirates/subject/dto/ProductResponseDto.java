package com.thepirates.subject.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
public class ProductResponseDto {
    private String name;
    private String description;
    private String price;
    @JsonIgnore
    private LocalDateTime createdAt;

    @Builder
    public ProductResponseDto(String name, String description, Integer price, LocalDateTime createdAt) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        this.name = name;
        this.description = description;
        this.price = decimalFormat.format(price) + " ~ "; //금액 String 콤마 형식 변환
        this.createdAt = createdAt;
    }
}
