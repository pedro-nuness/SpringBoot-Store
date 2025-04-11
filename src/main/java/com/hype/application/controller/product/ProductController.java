package com.hype.application.controller.product;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.hype.application.components.FileStorageProperties;
import com.hype.application.domain.product.Product;
import com.hype.application.domain.product.ProductRequestDTO;
import com.hype.application.domain.product.ProductResponseDTO;
import com.hype.application.domain.product.productimage.ProductImage;
import com.hype.application.domain.product.productimage.ProductImageRequestDTO;
import com.hype.application.exceptions.EventErrorTransferingFile;
import com.hype.application.exceptions.EventInvalidJsonException;
import com.hype.application.exceptions.EventNotFoundException;
import com.hype.application.respositories.product.ProductRepository;
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
    ProductRepository repository;

    @Autowired
    private FileStorageProperties fileStorageProperties;

    private static final ObjectMapper mapper = new ObjectMapper();

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity postProduct( @RequestParam("data") @Valid String dataJson,
                                       @RequestParam("image") MultipartFile images) throws Exception{
        ProductRequestDTO productDTO;
        try {
            productDTO = mapper.readValue(dataJson, ProductRequestDTO.class);
        } catch (Exception e) {
           throw new EventInvalidJsonException("Invalid Json", e.getMessage());
        }

        String filename = UUID.randomUUID().toString();
        String uploadPath = fileStorageProperties.getUploadDir() + filename;
        String imageUrl = fileStorageProperties.getBaseUrl() + filename;

        try {
            images.transferTo(new File(uploadPath));
        } catch (Exception e) {
           throw new EventErrorTransferingFile("An error occurred while uploading file: ", e.getMessage());
        }

        ProductImage productImage = new ProductImage(new ProductImageRequestDTO(imageUrl));
        Product newProduct = new Product(productDTO);

        productImage.setProduct(newProduct);
        newProduct.getImages().add(productImage);


        this.repository.save(newProduct);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity getAllProducts(){
        List<ProductResponseDTO> productList = this.repository.findAll().stream().map(ProductResponseDTO::new).toList();

        return ResponseEntity.ok(productList);
    }

    @DeleteMapping
    public ResponseEntity deleteProduct(@RequestParam("id") @NotBlank String id) {
        if(!this.repository.existsById(id)){
            throw new EventNotFoundException("Product not found");
        }
        this.repository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}