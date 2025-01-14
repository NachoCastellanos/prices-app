package com.example.prices_app.controller;

import com.example.prices_app.dto.PriceResponseDto;
import com.example.prices_app.entity.Price;
import com.example.prices_app.service.PriceService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/prices")
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/search")
    public ResponseEntity<PriceResponseDto> searchPrice(
            @RequestParam("brandId") Integer brandId,
            @RequestParam("productId") Long productId,
            @RequestParam("applicationDate")
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime applicationDate
    ) {
        Optional<Price> priceOpt = priceService.findApplicablePrice(brandId, productId, applicationDate);

        if (priceOpt.isEmpty()) {
            // Podrías retornar 404, o un mensaje de error, etc.
            return ResponseEntity.notFound().build();
        }

        // Convertimos Price a DTO
        Price price = priceOpt.get();
        PriceResponseDto dto = new PriceResponseDto(
                price.getProductId(),
                price.getBrandId(),
                price.getPriceList(),
                price.getStartDate(),
                price.getEndDate(),
                price.getPrice(),
                price.getCurr()
        );
        return ResponseEntity.ok(dto);
    }
}
