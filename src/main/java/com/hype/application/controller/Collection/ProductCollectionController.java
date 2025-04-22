package com.hype.application.controller.Collection;


import com.hype.application.domain.ProductCollection.Collection;
import com.hype.application.dto.collection.CollectionRequestDTO;
import com.hype.application.dto.collection.CollectionResponseDTO;
import com.hype.application.service.collection.ProductCollectionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/collection")
public class ProductCollectionController {

    @Autowired
    ProductCollectionServices productCollectionServices;


    @PostMapping
    public ResponseEntity<CollectionResponseDTO> addProduct(@RequestBody CollectionRequestDTO dto) {
        Collection collection = new Collection(dto);
        collection = productCollectionServices.AddCollection(collection);
        return ResponseEntity.ok(new CollectionResponseDTO(collection));
    }

    @GetMapping("{id}")
    public ResponseEntity<CollectionResponseDTO> getProduct(@PathVariable("id") String id) {
        Collection collection = productCollectionServices.GetCollection(id);
        return ResponseEntity.ok(new CollectionResponseDTO(collection));
    }

    @GetMapping()
    public ResponseEntity<List<CollectionResponseDTO>> getAllProduct() {
        List<CollectionResponseDTO> collections = productCollectionServices.GetAllCollections().stream().map(CollectionResponseDTO::new).toList();
        return ResponseEntity.ok(collections);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") String id) {
        productCollectionServices.RemoveCollection(id);
        return ResponseEntity.ok().build();
    }


    @PostMapping(value="{id}/image",  consumes = {MediaType.MULTIPART_FORM_DATA_VALUE } )
    public ResponseEntity<String> AddImageToProduct(@PathVariable("id") String id,
                                                    @RequestParam("image") MultipartFile image
                                                    ) {
        productCollectionServices.AddImageToCollection(id, image);
        return ResponseEntity.ok().build();
    }




    @DeleteMapping("{id}/image/{imageId}")
    public ResponseEntity<String> RemoveImageFromProduct(@PathVariable("id") String ID,
                                                          @PathVariable("imageId") String imageId) {
        productCollectionServices.RemoveImageFromCollection(ID, imageId);
        return ResponseEntity.ok().build();
    }
}
