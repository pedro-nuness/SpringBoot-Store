package com.hype.application.repository.product;


import com.hype.application.domain.ProductVariation.ProductVariation;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductVariationRepository extends JpaRepository<ProductVariation, String > {
}
