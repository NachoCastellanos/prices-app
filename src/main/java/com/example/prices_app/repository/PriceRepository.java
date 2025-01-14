package com.example.prices_app.repository;

import com.example.prices_app.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    List<Price> findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            Integer brandId,
            Long productId,
            LocalDateTime startDate,
            LocalDateTime endDate
    );
}
