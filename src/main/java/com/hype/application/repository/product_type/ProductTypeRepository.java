package com.hype.application.repository.product_type;

import com.hype.application.domain.ProductType.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductType, String> {
}

