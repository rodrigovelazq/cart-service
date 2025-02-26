package com.rodrigovelazq.cart_service.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductsAddedDto {
    private Long id;
    private LocalDateTime dateAdded;
    private BigDecimal quantity;
    private Long productId;
    private String productTitle;
    private BigDecimal productPrice;
    private String productImage;
    @JsonIgnore
    private CartDto cartDto;
}
