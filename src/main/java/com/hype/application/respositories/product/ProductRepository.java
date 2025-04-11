package com.hype.application.respositories.product;

import com.hype.application.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ProductRepository extends JpaRepository<Product, String> {
}