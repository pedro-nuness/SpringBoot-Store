package com.hype.application.respositories;

import com.hype.application.domain.product_image.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, String> {
}