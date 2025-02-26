package com.rodrigovelazq.cart_service.controller;

import com.rodrigovelazq.cart_service.domain.Cart;
import com.rodrigovelazq.cart_service.domain.dto.CartDto;
import com.rodrigovelazq.cart_service.domain.dto.ProductDto;
import com.rodrigovelazq.cart_service.domain.dto.ProductsAddedDto;
import com.rodrigovelazq.cart_service.mapper.CartMapper;
import com.rodrigovelazq.cart_service.service.CartService;
import com.rodrigovelazq.cart_service.service.client.ProductFeignClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;
    private final CartMapper cartMapper = Mappers.getMapper(CartMapper.class);
    private final ProductFeignClient productFeignClient;

    @GetMapping("/{id}/products")
    public ResponseEntity<List<ProductsAddedDto>> getProductByCart(@PathVariable("id") Long id) {
        Optional<Cart> found = cartService.findById(id);
        if (found.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        CartDto cartDto = cartMapper.mapTo(found.get());
        List<ProductsAddedDto> enrichedProducts = cartDto.getProductsAddedDtoList()
                .stream()
                .map(product -> {
                    try {
                        ResponseEntity<ProductDto> productDtoResponseEntity = productFeignClient.fetchProductDetails(product.getProductId());
                        ProductDto productDetails = productDtoResponseEntity.getBody();
                        product.setProductTitle(productDetails != null ? productDetails.getTitle() : null);
                        product.setProductPrice(productDetails != null ? productDetails.getPrice() : null);
                        product.setProductImage(productDetails != null ? productDetails.getImage() : null);
                    } catch (FeignException e) {
                        product.setProductTitle("Unknown Product");
                    }
                    return product;
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(enrichedProducts, HttpStatus.OK);
    }
}
