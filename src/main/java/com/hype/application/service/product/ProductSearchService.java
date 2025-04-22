package com.hype.application.service.product;


import com.hype.application.domain.Product.Product;
import com.hype.application.dto.product.ProductResponseDTO;
import com.hype.application.exception.EventNotFoundException;
import com.hype.application.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductSearchService {

    @Autowired
    ProductRepository productRepository;

    public List<ProductResponseDTO> searchProduct(String search) {
        List<Product> products = productRepository.findByNameContainingIgnoreCase(search);
        return products.stream()
                .map(ProductResponseDTO::new)
                .toList();
    }

    public Page<ProductResponseDTO> searchProduct(String search, Pageable pageable) {
        return productRepository
                .findByNameContainingIgnoreCase(search, pageable)
                .map(ProductResponseDTO::new);
    }


    public List<Product> getNewestProduct(int limit) {
        int quantity = (limit / 5) * 5;
        if (quantity == 0) return new ArrayList<>();

        Pageable pageable = PageRequest.of(0, quantity, Sort.by(Sort.Direction.DESC, "createdAt"));
        return productRepository.findAll(pageable).getContent();
    }

    public Page<ProductResponseDTO> getPagedProduct(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(ProductResponseDTO::new);
    }


}
