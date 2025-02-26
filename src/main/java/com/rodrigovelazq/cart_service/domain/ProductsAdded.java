package com.rodrigovelazq.cart_service.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "products_added")
public class ProductsAdded {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "date_added")
    private LocalDateTime dateAdded;
    @Column(name = "quantity")
    private BigDecimal quantity;
    @Column(name = "product_id")
    private Long productId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Override
    public String toString() {
        return "ProductsAdded{id=" + id + ", cartId=" + (cart != null ? cart.getId() : null) + "}";
    }
}
