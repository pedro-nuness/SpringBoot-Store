package com.hype.application.repository.product;

import com.hype.application.domain.ProductVariation.VariationSize.VariationSize;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VariationSizeRepository extends JpaRepository<VariationSize, String> {
}
