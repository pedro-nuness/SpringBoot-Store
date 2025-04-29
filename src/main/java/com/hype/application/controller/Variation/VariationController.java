package com.hype.application.controller.Variation;


import com.hype.application.domain.ProductVariation.ProductVariation;
import com.hype.application.dto.variation.ProductVariationDTO;
import com.hype.application.dto.variation.ProductVariationRequestDTO;
import com.hype.application.service.product.ProductVariationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/variation")
public class VariationController {

    @Autowired
    ProductVariationServices productVariationServices;


    @GetMapping
    public ResponseEntity<List<ProductVariationDTO>> findAll() {
        List<ProductVariationDTO> variations = productVariationServices.getAllProductVariations().stream()
                .map(ProductVariationDTO::new).toList();

        return ResponseEntity.ok(variations);
    }

    @PutMapping(value="/{variationId}/image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE } )
    public ResponseEntity<ProductVariationDTO> addImageToVariation(@PathVariable("variationId") String variationId,
                                                                   @RequestParam("image") MultipartFile image ) {

        return ResponseEntity.ok( new ProductVariationDTO(productVariationServices.addImage(variationId, image)));
    }

    @DeleteMapping(value="/{variationId}/image/{imageId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE } )
    public ResponseEntity<String> removeImageFromVariation(@PathVariable("variationId") String variationId,
                                                         @PathVariable("imageId") String imageId ) {
        productVariationServices.deleteImage(variationId, imageId);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{variationId}")
    public ResponseEntity<Void> updateVariation(@PathVariable("variationId") String variationId,
                                                @RequestBody ProductVariationRequestDTO dto) {
      //  productVariationServices.addImage(id, variationId, new ProductVariation(dto));
        return ResponseEntity.ok().build();
    }
}
