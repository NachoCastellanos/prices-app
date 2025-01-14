package com.example.prices_app.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class PriceResponseDto {
    private Long productId;
    private Integer brandId;
    private Integer priceList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double price;
    private String curr;

    public PriceResponseDto(Long productId, Integer brandId, Integer priceList,
                            LocalDateTime startDate, LocalDateTime endDate,
                            Double price, String curr) {
        this.productId = productId;
        this.brandId = brandId;
        this.priceList = priceList;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.curr = curr;
    }

    // getters, setters
}
