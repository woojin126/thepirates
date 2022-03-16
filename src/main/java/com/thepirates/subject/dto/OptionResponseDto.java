package com.thepirates.subject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OptionResponseDto {
    private Long productId;
    private String name;
    private Integer price;
}
