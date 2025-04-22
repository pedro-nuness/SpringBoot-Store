package com.hype.application.repository.category;

import com.hype.application.domain.ProductCategory.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
}