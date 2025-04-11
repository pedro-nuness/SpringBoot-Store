package com.hype.application.respositories.product;

import com.hype.application.domain.product.productimage.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, String> {
}