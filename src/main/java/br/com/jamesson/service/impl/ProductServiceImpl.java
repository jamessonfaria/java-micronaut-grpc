package br.com.jamesson.service.impl;

import br.com.jamesson.dto.ProductRequestDto;
import br.com.jamesson.dto.ProductResponseDto;
import br.com.jamesson.repository.ProductRepository;
import br.com.jamesson.service.ProductService;
import jakarta.inject.Singleton;

@Singleton
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDto create(ProductRequestDto requestDto) {
        return null;
    }
}
