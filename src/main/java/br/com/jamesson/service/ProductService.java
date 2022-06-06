package br.com.jamesson.service;

import br.com.jamesson.dto.ProductRequestDto;
import br.com.jamesson.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {

    ProductResponseDto create(ProductRequestDto requestDto);
    List<ProductResponseDto> getAll();

}
