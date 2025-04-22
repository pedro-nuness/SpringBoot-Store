package com.hype.application.controller.Product;

import com.hype.application.dto.product.ProductResponseDTO;
import com.hype.application.service.product.ProductSearchService;
import com.hype.application.service.product.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductSearchController {

    @Autowired
    ProductSearchService productSearchService;

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponseDTO>> search(@RequestParam("q") String search) {
        return ResponseEntity.ok(productSearchService.searchProduct(search));
    }

    @GetMapping("/search/pageable")
    public ResponseEntity<Page<ProductResponseDTO>> searchPaged(
            @RequestParam("q") String search,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(productSearchService.searchProduct(search, pageable));
    }

    @GetMapping("/news")
    public ResponseEntity<List<ProductResponseDTO>> getNewProducts(){
        List<ProductResponseDTO> productList =
                this.productSearchService.getNewestProduct(20).stream().map(ProductResponseDTO::new).toList();
        return ResponseEntity.ok(productList);
    }
}