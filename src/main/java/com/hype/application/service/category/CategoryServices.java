package com.hype.application.service.category;


import com.hype.application.domain.Product.Product;
import com.hype.application.domain.ProductCategory.Category;
import com.hype.application.domain.ProductCategory.CategoryImage.CategoryImage;
import com.hype.application.dto.category.CategoryRequestDTO;
import com.hype.application.dto.category.CategoryResponseDTO;
import com.hype.application.exception.EventNotFoundException;
import com.hype.application.repository.category.CategoryImageRepository;
import com.hype.application.repository.category.CategoryRepository;
import com.hype.application.service.public_files.PublicFilesServices;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Getter
public class CategoryServices {

    @Autowired
    private PublicFilesServices publicFilesServices;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryImageRepository categoryImageRepository;

    public Category createCategory(CategoryRequestDTO dto) {
        Category category = new Category(dto);
        return categoryRepository.save(category);
    }

    public List<CategoryResponseDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(CategoryResponseDTO::new)
                .collect(Collectors.toList());
    }

    public Category getCategoryById(String id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        return categoryOptional.orElseThrow(EventNotFoundException::new);
    }

    public CategoryResponseDTO updateCategory(String id, CategoryRequestDTO categoryRequestDTO) {
        Category category = getCategoryById(id);

        category.setName(categoryRequestDTO.name());
        categoryRepository.save(category);
        return new CategoryResponseDTO(category);
    }

    public void deleteCategory(String id) {
        categoryRepository.deleteById(id);
    }

    public Category addImageToCategory(String categoryId, MultipartFile image) {

        Category category = getCategoryById(categoryId);

        String Url = publicFilesServices.UploadFile(image);
        CategoryImage categoryImage = new CategoryImage(Url, category);
        category.getImages().add(categoryImage);

        categoryRepository.save(category);

        return category;
    }

    public Category RemoveImageFromCategory(String categoryId, String ImageID) {

        Category category = getCategoryById(categoryId);

        category.getImages().removeIf(image -> image.getId().equals(ImageID));

        categoryRepository.save(category);

        return category;
    }

    public List<Product> GetProductsOnCategory(String ID){
        Category category = getCategoryById(ID);
        return category.getProducts();
    }


}
