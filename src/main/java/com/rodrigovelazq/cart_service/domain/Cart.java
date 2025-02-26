package com.rodrigovelazq.cart_service.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "created")
    private LocalDateTime created;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
    private List<ProductsAdded> productsAddedList = new ArrayList<>();

    @Override
    public String toString() {
        return "Cart{id=" + id + "}";
    }
}
