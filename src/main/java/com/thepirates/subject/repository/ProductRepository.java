package com.thepirates.subject.repository;

import com.thepirates.subject.dto.ProductDetailResponseDto;
import com.thepirates.subject.dto.ProductResponseDto;
import com.thepirates.subject.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(value = " SELECT distinct new com.thepirates.subject.dto.ProductResponseDto (i.name,i.description, min(o.price)) " +
            " from Product i join i.optionsList o on i.id = o.item.id" +
            " group by i.name ")
    List<ProductResponseDto> findAllByItem();


    @Query(value = " select distinct new com.thepirates.subject.dto.ProductDetailResponseDto (t.name,t.description,t.delivery.type) from Product t where t.id =:productId")
    Optional<ProductDetailResponseDto> findOneById(@Param("productId") Long productId);
}
