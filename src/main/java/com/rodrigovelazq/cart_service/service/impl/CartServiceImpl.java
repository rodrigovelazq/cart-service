package com.rodrigovelazq.cart_service.service.impl;

import com.rodrigovelazq.cart_service.domain.Cart;
import com.rodrigovelazq.cart_service.exception.ResourceNotFoundException;
import com.rodrigovelazq.cart_service.repository.CartRepository;
import com.rodrigovelazq.cart_service.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    @Override
    public Page<Cart> findAll(Pageable pageable) {
        return cartRepository.findAll(pageable);
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public boolean isExists(Long id) {
        return cartRepository.existsById(id);
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public Cart partialUpdate(Long id, Cart cart) {
        cart.setId(id);
        return cartRepository.findById(id).map(existingCart -> {
            Optional.ofNullable(cart.getCreated()).ifPresent(existingCart::setCreated);
            Optional.ofNullable(cart.getProductsAddedList()).ifPresent(existingCart::setProductsAddedList);
            return cartRepository.save(existingCart);
        }).orElseThrow(() -> new ResourceNotFoundException("Cart", "id", id.toString()));
    }

    @Override
    public void delete(Long id) {
        cartRepository.deleteById(id);
    }
}
