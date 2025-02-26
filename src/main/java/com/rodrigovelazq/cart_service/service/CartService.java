package com.rodrigovelazq.cart_service.service;

import com.rodrigovelazq.cart_service.domain.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CartService {
    Page<Cart> findAll(Pageable pageable);

    Cart save(Cart cart);

    boolean isExists(Long id);

    Optional<Cart> findById(Long id);

    Cart partialUpdate(Long id, Cart cart);

    void delete(Long id);
}
