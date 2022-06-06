package br.com.jamesson.service.impl;

import br.com.jamesson.domain.Product;
import br.com.jamesson.dto.ProductRequestDto;
import br.com.jamesson.dto.ProductResponseDto;
import br.com.jamesson.repository.ProductRepository;
import br.com.jamesson.service.ProductService;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDto create(ProductRequestDto requestDto) {
        var product = new Product(requestDto.name(), requestDto.price());
        Product productSaved = productRepository.save(product);
        return ProductResponseDto.fromEntityToDto(productSaved);
    }

    @Override
    public List<ProductResponseDto> getAll() {
        List<Product> products = productRepository.findAll();
        List<ProductResponseDto> collect = products.stream().map(e -> {
            var p = new ProductResponseDto(e.getId(), e.getName(), e.getPrice());
            return p;
        }).collect(Collectors.toList());
        return collect;
    }
}
