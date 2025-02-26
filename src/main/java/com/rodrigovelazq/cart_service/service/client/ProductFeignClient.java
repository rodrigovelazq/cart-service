package com.rodrigovelazq.cart_service.service.client;

import com.rodrigovelazq.cart_service.domain.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service")
public interface ProductFeignClient {

    @GetMapping(value = "/products/{id}", consumes = "application/json")
    public ResponseEntity<ProductDto> fetchProductDetails(@PathVariable("id") Long id);
}
