package com.example.prices_app;

import com.example.prices_app.dto.PriceResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PriceIntegrationTest {

    @LocalServerPort
    private int port;

    private final TestRestTemplate restTemplate = new TestRestTemplate();

    private String formatDate(LocalDateTime dt) {
        return dt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Test
    void test1() {
        // Petición a las 10:00 del día 14
        String url = String.format("http://localhost:%d/api/prices/search?brandId=1&productId=35455&applicationDate=%s",
                port, formatDate(LocalDateTime.of(2020, 6, 14, 10, 0)));
        PriceResponseDto response = restTemplate.getForObject(url, PriceResponseDto.class);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(1, response.getPriceList());  // Tarifa 1
        Assertions.assertEquals(35.50, response.getPrice());
    }

    @Test
    void test2() {
        // Petición a las 16:00 del día 14
        String url = String.format("http://localhost:%d/api/prices/search?brandId=1&productId=35455&applicationDate=%s",
                port, formatDate(LocalDateTime.of(2020, 6, 14, 16, 0)));
        PriceResponseDto response = restTemplate.getForObject(url, PriceResponseDto.class);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(2, response.getPriceList());  // Tarifa 2
        Assertions.assertEquals(25.45, response.getPrice());
    }

    @Test
    void test3() {
        // Petición a las 21:00 del día 14
        String url = String.format("http://localhost:%d/api/prices/search?brandId=1&productId=35455&applicationDate=%s",
                port, formatDate(LocalDateTime.of(2020, 6, 14, 21, 0)));
        PriceResponseDto response = restTemplate.getForObject(url, PriceResponseDto.class);

        Assertions.assertNotNull(response);
        // Aplica la tarifa 1 porque la 2 termina a las 18:30
        Assertions.assertEquals(1, response.getPriceList());
        Assertions.assertEquals(35.50, response.getPrice());
    }

    @Test
    void test4() {
        // Petición a las 10:00 del día 15
        String url = String.format("http://localhost:%d/api/prices/search?brandId=1&productId=35455&applicationDate=%s",
                port, formatDate(LocalDateTime.of(2020, 6, 15, 10, 0)));
        PriceResponseDto response = restTemplate.getForObject(url, PriceResponseDto.class);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(3, response.getPriceList());
        Assertions.assertEquals(30.50, response.getPrice());
    }

    @Test
    void test5() {
        // Petición a las 21:00 del día 16
        String url = String.format("http://localhost:%d/api/prices/search?brandId=1&productId=35455&applicationDate=%s",
                port, formatDate(LocalDateTime.of(2020, 6, 16, 21, 0)));
        PriceResponseDto response = restTemplate.getForObject(url, PriceResponseDto.class);

        Assertions.assertNotNull(response);
        // Tarifa 4
        Assertions.assertEquals(4, response.getPriceList());
        Assertions.assertEquals(38.95, response.getPrice());
    }
}
