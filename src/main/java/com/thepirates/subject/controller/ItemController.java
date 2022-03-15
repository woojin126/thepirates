package com.thepirates.subject.controller;

import com.thepirates.subject.dto.ProductRequestDto;
import com.thepirates.subject.dto.ProductResponseDto;
import com.thepirates.subject.entity.Item;
import com.thepirates.subject.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/api/product")
    public Long itemSave(@RequestBody ProductRequestDto request) {
        return itemService.saveProduct(request);
    }

    @GetMapping("/api/product")
    public List<ProductResponseDto> getItem() {
        return itemService.findAllProduct();
    }
}
