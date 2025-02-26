package com.rodrigovelazq.cart_service.mapper;

import com.rodrigovelazq.cart_service.domain.ProductsAdded;
import com.rodrigovelazq.cart_service.domain.dto.ProductDto;
import com.rodrigovelazq.cart_service.domain.dto.ProductsAddedDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductsAddedMapper {
    ProductsAddedMapper INSTANCE = Mappers.getMapper(ProductsAddedMapper.class);

    ProductsAddedDto mapTo(ProductsAdded productsAdded);
    ProductsAdded mapFrom(ProductsAddedDto productsAddedDto);
}