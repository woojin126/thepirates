package com.thepirates.subject.service;

import com.thepirates.subject.dto.ProductRequestDto;
import com.thepirates.subject.dto.ProductResponseDto;
import com.thepirates.subject.entity.Item;
import com.thepirates.subject.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = false)
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public Long saveProduct(ProductRequestDto request) {
        Item createProduct = Item.createItem(request);
        Item saveProduct = itemRepository.save(createProduct);

        return saveProduct.getId();
    }

    public List<ProductResponseDto> findAllProduct() {

        return itemRepository.findAllByItem();
    }
}
