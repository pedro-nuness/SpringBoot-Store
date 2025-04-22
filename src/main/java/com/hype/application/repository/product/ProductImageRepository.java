package com.hype.application.repository.product;

import com.hype.application.domain.ProductImage.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, String> {
}