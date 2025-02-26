package com.rodrigovelazq.cart_service.mapper;

import com.rodrigovelazq.cart_service.domain.Cart;
import com.rodrigovelazq.cart_service.domain.ProductsAdded;
import com.rodrigovelazq.cart_service.domain.dto.CartDto;
import com.rodrigovelazq.cart_service.domain.dto.ProductsAddedDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(uses = ProductsAddedMapper.class)
public interface CartMapper {
    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    @Mapping(target = "productsAddedDtoList", source = "productsAddedList", qualifiedByName = "mapProducts")
    CartDto mapTo(Cart cart);

    CartDto mapFrom(CartDto cartDto);

    @Named("mapProducts")
    static List<ProductsAddedDto> mapProducts(List<ProductsAdded> products) {
        return products == null ? new ArrayList<>() : products.stream()
                .map(ProductsAddedMapper.INSTANCE::mapTo)
                .collect(Collectors.toList());
    }
}

