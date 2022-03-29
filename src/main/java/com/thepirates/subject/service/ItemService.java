package com.thepirates.subject.service;

import com.thepirates.subject.dateUtils.DateCalculate;
import com.thepirates.subject.dto.*;
import com.thepirates.subject.entity.Product;
import com.thepirates.subject.repository.ProductRepository;
import com.thepirates.subject.repository.OptionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
        ProductDetailResponseDto productDetailResponseDto = productRepository.findOneByProductId(productId).orElseThrow(() -> new NullPointerException("아이템 아이디가 없습니다."));
        List<OptionResponseDto> optionList = optionsRepository.findOptionByProductId(productId).orElseThrow(() -> new NullPointerException("아이템 아이디가 없습니다."));
        productDetailResponseDto.saveOptions(optionList);

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
        //현재시간이 마감시간 이전이면 true 아니면 false
        boolean closeTime = now.isBefore(closingTime);
        //익일 배송, 당일 배송
        String slowOrFastDelivery = product.getDelivery().getType();
        //배달일자 계산
        List<String> deliveryDate = DateCalculate.getDeliveryDate(closeTime, slowOrFastDelivery);

        return deliveryDate.stream().map(s ->
                DateResponseDto.builder()
                .date(s)
                .build()
        ).collect(Collectors.toList());
    }

    // 상품 삭제
    @Transactional
    public Long deleteProduct(Long productId) {
        productRepository.deleteById(productId);
        return productId;
    }
}
