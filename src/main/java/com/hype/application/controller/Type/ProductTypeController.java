package com.hype.application.controller.Type;

import com.hype.application.dto.product.ProductResponseDTO;
import com.hype.application.dto.type.ProductTypeRequestDTO;
import com.hype.application.dto.type.ProductTypeResponseDTO;
import com.hype.application.domain.ProductType.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hype.application.service.type.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/product_type")
public class ProductTypeController {

    @Autowired
    ProductTypeServices productTypeServices;

    @PostMapping
    public ResponseEntity<String> Control_AddProductType(@RequestBody ProductTypeRequestDTO productTypeRequestDTO) {
        ProductType productType = new ProductType(productTypeRequestDTO);
        productTypeServices.AddProductType(productType);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping( "{id}")
    public ResponseEntity<String> Control_deleteProductType(@PathVariable("id") String ID) {
        productTypeServices.DeleteProductType(ID);
        return ResponseEntity.ok().build();
    }

    @GetMapping( "{id}")
    public ResponseEntity<ProductTypeResponseDTO> Control_GetProductType(@PathVariable("id") String ID) {
        ProductType product = productTypeServices.GetProductType(ID);
        return ResponseEntity.ok(new ProductTypeResponseDTO(product));
    }

    @PutMapping( "{id}")
    public ResponseEntity<String> Control_UpdateProductType(@PathVariable("id") String ID,
                                                    @RequestBody ProductTypeRequestDTO productTypeRequestDTO) {
        productTypeServices.UpdateProductType(ID, new ProductType(productTypeRequestDTO));
        return ResponseEntity.ok().build();
    }

    @GetMapping( "")
    public ResponseEntity<List<ProductTypeResponseDTO>> Control_GetAllCategories() {
        List<ProductTypeResponseDTO> response = productTypeServices.GetAllProductTypes();
        return ResponseEntity.ok(response);
    }

    @PostMapping(value="{id}/image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<ProductTypeResponseDTO> AddImageToProductType(@PathVariable("id") String ID,
                                                                        @RequestParam("image") MultipartFile image) {
        ProductType productType = productTypeServices.AddImageToProductType(ID, image);
        return ResponseEntity.ok(new ProductTypeResponseDTO(productType));
    }

    @DeleteMapping("{id}/image/{image}")
    public ResponseEntity<ProductTypeResponseDTO> DeleteImageFromProductType(@PathVariable("id") String ID,
                                                                            @PathVariable("image") String ImageID) {
        ProductType productType = productTypeServices.RemoveImageFromProductType(ID, ImageID);
        return ResponseEntity.ok(new ProductTypeResponseDTO(productType));
    }

    @GetMapping("/{typeId}/products")
    public ResponseEntity<List<ProductResponseDTO>> GetProducts(@PathVariable("typeId") String categoryId) {
        List<ProductResponseDTO> Products = productTypeServices.GetProductsOnType(categoryId).stream().map(ProductResponseDTO::new).toList();
        return ResponseEntity.ok(Products);
    }
}
