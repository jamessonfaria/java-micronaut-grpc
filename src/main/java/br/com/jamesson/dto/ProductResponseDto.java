package br.com.jamesson.dto;

import br.com.jamesson.domain.Product;

public record ProductResponseDto(Long id, String name, Double price) {
    public static ProductResponseDto fromEntityToDto(Product product){
        return new ProductResponseDto(product.getId(), product.getName(), product.getPrice());
    }
}