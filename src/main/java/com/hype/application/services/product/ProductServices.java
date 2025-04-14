package com.hype.application.services.product;


import com.hype.application.components.FileStorageProperties;
import com.hype.application.domain.product.DTO.ProductResponseDTO;
import com.hype.application.domain.product.Product;
import com.hype.application.domain.product.productimage.ProductImage;
import com.hype.application.domain.product.productimage.ProductImageRequestDTO;
import com.hype.application.exceptions.EventErrorTransferingFile;
import com.hype.application.exceptions.EventNotFoundException;
import com.hype.application.respositories.product.ProductRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Getter
public class ProductServices {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FileStorageProperties fileStorageProperties;

    public void addProduct(Product product) {
        this.productRepository.save(product);
    }

    public Product AddImageToProduct(String ProductID, MultipartFile image) {
        Product product = getProduct(ProductID);

        String filename = UUID.randomUUID().toString();
        String uploadPath = fileStorageProperties.getUploadDir() + filename;
        String imageUrl = fileStorageProperties.getBaseUrl() + filename;

        try {
            image.transferTo(new File(uploadPath));
        } catch (Exception e) {
            throw new EventErrorTransferingFile("An error occurred while uploading file: ", e.getMessage());
        }

        ProductImage productImage = new ProductImage(new ProductImageRequestDTO(imageUrl));
        productImage.setProduct(product);
        product.getImages().add(productImage);

        this.productRepository.save(product);
        return product;
    }

    public Product DeleteImageFromProduct(String productId, String imageId) {
        Product product = getProduct(productId);
        boolean removed = product.getImages().removeIf(image -> image.getId().equals(imageId));

        if (!removed) {
            throw new EventNotFoundException();
        }

         productRepository.save(product);

        return product;
    }


    public Product getProduct(String ProductID) {
        Optional<Product> optionalProduct = productRepository.findById(ProductID);

        if(optionalProduct.isEmpty())
            throw new EventNotFoundException();

        return optionalProduct.get();
    }

    public List<ProductResponseDTO> getPagedProduct(int Page, int PageLimit) {
        List<ProductResponseDTO> productList = this.productRepository.findAll()
                .stream()
                .map(ProductResponseDTO::new)
                .toList();


        int fromIndex = Page * PageLimit;
        int toIndex = Math.min(fromIndex + PageLimit, productList.size());

        if (fromIndex > productList.size()) {
            return null;
        }

        return productList.subList(fromIndex, toIndex);
    }

    public List<ProductResponseDTO> getAllProducts() {
        List<ProductResponseDTO> productList = this.productRepository.findAll()
                .stream()
                .map(ProductResponseDTO::new)
                .toList();

        return productList;
    }

    public void deleteProduct(String ProductID) {
        //Throws not found exception in case of not found
        Product product = getProduct(ProductID);

        this.productRepository.deleteById(ProductID);
    }
}
