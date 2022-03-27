package com.thepirates.subject.controller;

import com.thepirates.subject.dto.DateResponseDto;
import com.thepirates.subject.dto.ProductDetailResponseDto;
import com.thepirates.subject.dto.ProductRequestDto;
import com.thepirates.subject.dto.ProductResponseDto;
import com.thepirates.subject.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ItemController {

    private final ItemService itemService;

    //상품추가
    @PostMapping("/api/product")
    public Long itemSave(@RequestBody ProductRequestDto request) {
        return itemService.addProduct(request);
    }

    //상품 조회
    @GetMapping("/api/product")
    public List<ProductResponseDto> getItem() {
        return itemService.findAllByProduct();
    }

    //상품 상세조회
    @GetMapping("/api/{productId}/product")
    public ProductDetailResponseDto getDetailItem(@PathVariable Long productId) {
        return itemService.findByDetailProduct(productId);
    }

    //상품 수령일 선택 목록 API
    @GetMapping("/api/{productId}/deliveryDate")
    public List<DateResponseDto> getDeliveryDate(@PathVariable Long productId) throws Exception {
        return itemService.findBySelectDeliveryDate(productId, LocalDateTime.now());
    }

    // 상품 삭제
    @DeleteMapping("/api/product/{id}")
    public void deleteProduct(@PathVariable Long id) {
        itemService.deleteProduct(id);
    }
}
