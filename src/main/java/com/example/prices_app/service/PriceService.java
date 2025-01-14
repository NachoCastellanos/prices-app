package com.example.prices_app.service;

import com.example.prices_app.entity.Price;
import com.example.prices_app.repository.PriceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service

public class PriceService {

    private final PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Optional<Price> findApplicablePrice(Integer brandId, Long productId, LocalDateTime applicationDate) {
        List<Price> prices = priceRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                brandId, productId, applicationDate, applicationDate
        );

        return prices.stream()
                .max(Comparator.comparing(Price::getPriority));
    }
}
