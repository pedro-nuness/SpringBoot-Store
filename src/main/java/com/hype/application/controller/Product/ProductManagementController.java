package com.hype.application.controller.Product;

import com.hype.application.dto.product.ProductRequestDTO;
import com.hype.application.dto.product.ProductResponseDTO;
import com.hype.application.domain.Product.Product;
import com.hype.application.domain.ProductCategory.Category;
import com.hype.application.domain.ProductType.ProductType;
import com.hype.application.service.category.CategoryServices;
import com.hype.application.service.product.ProductServices;
import com.hype.application.service.type.ProductTypeServices;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductManagementController {

    @Autowired
    ProductServices productServices;
    @Autowired
    CategoryServices categoryServices;
    @Autowired
    ProductTypeServices productTypeServices;

    @GetMapping("")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(){
        List<ProductResponseDTO> productList =
                this.productServices.getAllProducts();
        return ResponseEntity.ok(productList);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable String id){
       ProductResponseDTO productList = new ProductResponseDTO(this.productServices.getProduct(id));
        return ResponseEntity.ok(productList);
    }

    @PostMapping
    public ResponseEntity<Void> postProduct(@RequestBody ProductRequestDTO dto) {
        Category category = categoryServices.getCategoryById(dto.category());
        ProductType productType = productTypeServices.GetProductType(dto.product_type());

        Product newproduct = new Product(dto);

        category.getProducts().add(newproduct);
        productType.getProducts().add(newproduct);

        newproduct.setCategory(category);
        newproduct.setProduct_type(productType);

        productServices.addProduct(newproduct);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productServices.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAll() {
        productServices.deleteAllProducts();
        return ResponseEntity.ok().build();
    }

    @PostMapping(value="{id}/image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE } )
    public ResponseEntity<ProductResponseDTO> AddImage(@PathVariable("id") @NotBlank String ID, @RequestParam("image") MultipartFile image) {
        Product requestedProduct = productServices.AddImageToProduct(ID, image);
        return ResponseEntity.ok(new ProductResponseDTO(requestedProduct));
    }

    @DeleteMapping("{id}/image/{image_id}")
    public ResponseEntity<ProductResponseDTO> DeleteImage(@PathVariable("id") @NotBlank String ID,
                                                          @PathVariable("image_id") @NotBlank String ImageID) {
        Product requestedProduct = productServices.deleteImageFromProduct(ID, ImageID);
        return ResponseEntity.ok(new ProductResponseDTO(requestedProduct));
    }
}