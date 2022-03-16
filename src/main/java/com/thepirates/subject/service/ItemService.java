package com.thepirates.subject.service;

import com.thepirates.subject.dto.*;
import com.thepirates.subject.entity.Product;
import com.thepirates.subject.repository.ProductRepository;
import com.thepirates.subject.repository.OptionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = false)
@Service
public class ItemService {

    private final ProductRepository productRepository;
    private final OptionsRepository optionsRepository;

    //상품 추가
    @Transactional
    public Long addProduct(ProductRequestDto request) {
        Product createProduct = Product.createItem(request);
        Product saveProduct = productRepository.save(createProduct);

        return saveProduct.getId();
    }

    //상품 조회
    public List<ProductResponseDto> findAllByProduct() {
        return productRepository.findAllByItem();
    }

    //상품 상세 조회
    public ProductDetailResponseDto findByDetailProduct(Long productId) {
        ProductDetailResponseDto productDetailResponseDto = productRepository.findOneById(productId).orElseThrow(() -> new NullPointerException("아이템 아이디가 없습니다."));
        List<OptionResponseDto> optionByProductId = optionsRepository.findOptionByProductId(productId);
        productDetailResponseDto.setOptions(optionByProductId);

        return productDetailResponseDto;
    }

    //상품 수령일 선택 목록 API
    public List<DateResponseDto> findBySelectDeliveryDate(Long productId, LocalDateTime now) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new NullPointerException("물품 아이디가 없습니다."));

        LocalDateTime closingTime = LocalDateTime.of(now.getYear(),
                now.getMonth(),
                now.getDayOfMonth(),
                product.getDelivery().getClosing().getHour(),
                product.getDelivery().getClosing().getMinute());

        System.out.println(closingTime +"시간");
        System.out.println(LocalDateTime.from(closingTime).minusDays(1) + "시간2");
        return null;
    }

    // 상품 삭제
    @Transactional
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}
