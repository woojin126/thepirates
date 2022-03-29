package com.thepirates.subject.repository;

import com.thepirates.subject.dto.OptionResponseDto;
import com.thepirates.subject.entity.Options;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OptionsRepository extends JpaRepository<Options,Long> {

    @Query(value = "select new com.thepirates.subject.dto.OptionResponseDto ( o.item.id, o.name, o.price ) from Options o where o.item.id =:productId")
    Optional<List<OptionResponseDto>> findOptionByProductId(@Param("productId") Long productId);
}
