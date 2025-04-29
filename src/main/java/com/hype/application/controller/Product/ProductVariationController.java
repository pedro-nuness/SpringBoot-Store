package com.hype.application.controller.Product;

import com.hype.application.dto.variation.ProductVariationDTO;
import com.hype.application.dto.variation.ProductVariationRequestDTO;
import com.hype.application.domain.ProductVariation.ProductVariation;
import com.hype.application.service.product.ProductServices;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductVariationController {

    @Autowired
    ProductServices productServices;

    @GetMapping("/variation")
    public ResponseEntity<List<ProductVariationDTO>> getVariation() {
        return ResponseEntity.ok(productServices.getVariation().stream().map(ProductVariationDTO::new).toList());
    }

    @PostMapping("{id}/variation")
    public ResponseEntity<Void> addVariation(@PathVariable String id, @RequestBody ProductVariationRequestDTO dto) {
        productServices.addVariation(id, new ProductVariation(dto));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}/variation/{variationId}")
    public ResponseEntity<Void> removeVariation(@PathVariable String id, @PathVariable String variationId) {
        productServices.removeVariation(id, variationId);
        return ResponseEntity.ok().build();
    }
}