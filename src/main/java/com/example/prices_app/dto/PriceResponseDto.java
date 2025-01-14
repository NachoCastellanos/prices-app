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
    public Long getProductId() {
        return productId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public Integer getPriceList() {
        return priceList;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public Double getPrice() {
        return price;
    }

    public String getCurr() {
        return curr;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public void setPriceList(Integer priceList) {
        this.priceList = priceList;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setCurr(String curr) {
        this.curr = curr;
    }
}
