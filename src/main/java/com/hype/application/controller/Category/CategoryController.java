package com.hype.application.controller.Category;

import com.hype.application.dto.product.ProductResponseDTO;
import com.hype.application.domain.ProductCategory.Category;
import com.hype.application.dto.category.CategoryRequestDTO;
import com.hype.application.dto.category.CategoryResponseDTO;
import com.hype.application.service.category.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryServices categoryServices;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponseDTO createCategory(@RequestBody CategoryRequestDTO categoryRequestDTO) {
        Category category = categoryServices.createCategory(categoryRequestDTO);
        return new CategoryResponseDTO(category);
    }

    @GetMapping
    public List<CategoryResponseDTO> getAllCategories() {
        return categoryServices.getAllCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable String id) {
        return ResponseEntity.ok( new CategoryResponseDTO(categoryServices.getCategoryById(id)));
    }

    @PutMapping("/{id}")
    public CategoryResponseDTO updateCategory(@PathVariable String id, @RequestBody CategoryRequestDTO categoryRequestDTO) {
        return categoryServices.updateCategory(id, categoryRequestDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable String id) {
        categoryServices.deleteCategory(id);
    }

    @PostMapping(value="/{categoryId}/image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<CategoryResponseDTO> addImageToCategory(@PathVariable String categoryId,
                                                     @RequestParam("image") MultipartFile image) {
        Category category =  categoryServices.addImageToCategory(categoryId, image);
        return ResponseEntity.ok(new CategoryResponseDTO(category));
    }

    @DeleteMapping("/{categoryId}/image/{imageID}")
    public ResponseEntity<CategoryResponseDTO> RemoveImageFromCategory(@PathVariable("categoryId") String categoryId,
                                                                  @PathVariable("imageID") String ImageID) {
        Category category =  categoryServices.RemoveImageFromCategory(categoryId, ImageID);
        return ResponseEntity.ok(new CategoryResponseDTO(category));
    }

    @GetMapping("/{categoryId}/products/")
    public ResponseEntity<List<ProductResponseDTO>> GetProducts(@PathVariable("categoryId") String categoryId) {
        List<ProductResponseDTO> Products = categoryServices.GetProductsOnCategory(categoryId).stream().map(ProductResponseDTO::new).toList();
        return ResponseEntity.ok(Products);
    }
}