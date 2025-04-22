package com.hype.application.repository.category;

import com.hype.application.domain.ProductCategory.CategoryImage.CategoryImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryImageRepository extends JpaRepository<CategoryImage, String> {
}


