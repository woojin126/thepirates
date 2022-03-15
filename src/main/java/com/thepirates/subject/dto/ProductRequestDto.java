package com.thepirates.subject.dto;

import com.thepirates.subject.entity.Delivery;
import com.thepirates.subject.entity.Options;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {
        private String name;
        private String description;
        private Delivery delivery;
        private List<Options> options;
}
