package com.rodrigovelazq.cart_service.service.client;

import com.rodrigovelazq.cart_service.domain.dto.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductFallback implements ProductFeignClient{
    @Override
    public ResponseEntity<ProductDto> fetchProductDetails(Long id) {
        return null;
    }
}
