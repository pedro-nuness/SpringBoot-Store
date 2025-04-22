package com.hype.application.domain.ProductCategory.CategoryImage;


import com.hype.application.domain.Image.ImageEntity;
import com.hype.application.domain.ProductCategory.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Table(name = "category_image")
@Entity(name = "category_image")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryImage extends ImageEntity {

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public CategoryImage(String url, Category category) {
        super(url);
        this.category = category;
    }
}