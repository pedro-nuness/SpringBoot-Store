package com.hype.application.controller.product;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.hype.application.components.FileStorageProperties;
import com.hype.application.domain.product.Product;
import com.hype.application.domain.product.DTO.ProductRequestDTO;
import com.hype.application.domain.product.DTO.ProductResponseDTO;
import com.hype.application.domain.product.productimage.ProductImage;
import com.hype.application.domain.product.productimage.ProductImageRequestDTO;
import com.hype.application.exceptions.EventErrorTransferingFile;
import com.hype.application.exceptions.EventInvalidJsonException;
import com.hype.application.exceptions.EventNotFoundException;
import com.hype.application.respositories.product.ProductRepository;
import com.hype.application.services.product.ProductServices;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductServices productServices;

    private static final ObjectMapper mapper = new ObjectMapper();

    @PostMapping(value="{id}/image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE } )
    public ResponseEntity<ProductResponseDTO> AddImage(@PathVariable("id") @NotBlank String ID, @RequestParam("image") MultipartFile image) {
       Product requestedProduct = productServices.AddImageToProduct(ID, image);
       return ResponseEntity.ok(new ProductResponseDTO(requestedProduct));
    }

    @DeleteMapping("{id}/image/{image_id}")
    public ResponseEntity<ProductResponseDTO> DeleteImage(@PathVariable("id") @NotBlank String ID,
                                                          @PathVariable("image_id") @NotBlank String ImageID) {
        Product requestedProduct = productServices.DeleteImageFromProduct(ID, ImageID);
        return ResponseEntity.ok(new ProductResponseDTO(requestedProduct));
    }



    @PostMapping
    public ResponseEntity<ProductResponseDTO> postProduct( @RequestBody ProductRequestDTO dto ){
        Product newProduct = new Product(dto);
        productServices.addProduct(newProduct);
        return ResponseEntity.ok(new ProductResponseDTO(newProduct));
    }
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(){
        List<ProductResponseDTO> productList = this.productServices.getAllProducts();
        return ResponseEntity.ok(productList);
    }

    @GetMapping("/{page}/{page_limit}")
    public ResponseEntity<List<ProductResponseDTO>> getPageProducts(
            @PathVariable("page") int page,
            @PathVariable("page_limit") int page_limit) {

        List<ProductResponseDTO> pagedProducts = productServices.getPagedProduct(page, page_limit);
        return ResponseEntity.ok(pagedProducts);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable @NotBlank String id) {
        this.productServices.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}