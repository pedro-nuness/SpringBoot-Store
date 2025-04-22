package com.hype.application.controller.Product;

import com.hype.application.service.product.ProductServices;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductLinkCollectionController {

    @Autowired
    ProductServices productServices;

    @PutMapping("{id}/collection/{collectionID}")
    public ResponseEntity<String> AddCollection(@PathVariable("id") @NotBlank String id,
                                                @PathVariable("collectionID") String collectionID) {
        this.productServices.AddCollection(id, collectionID );


        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}/collection")
    public ResponseEntity<Void> removeCollection(@PathVariable String id) {
        productServices.RemoveCollection(id);
        return ResponseEntity.ok().build();
    }
}