package com.thepirates.subject.repository;

import com.thepirates.subject.dto.ProductResponseDto;
import com.thepirates.subject.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Long> {


/*    @Query(value = "SELECT distinct new com.thepirates.subject.dto.ProductResponseDto (i.name,i.description, o.price) " +
            "from Item i left join i.optionsList o ")
    List<ProductResponseDto> findAllByItem();*/


    @Query(value = " SELECT distinct new com.thepirates.subject.dto.ProductResponseDto (i.name,i.description, min(o.price)) " +
            " from Item i join i.optionsList o on i.id = o.item.id" +
            " group by i.name ")
    List<ProductResponseDto> findAllByItem();



}
