package br.com.jamesson.service;

import br.com.jamesson.dto.ProductRequestDto;
import br.com.jamesson.dto.ProductResponseDto;

public interface ProductService {

    ProductResponseDto create(ProductRequestDto requestDto);

}
