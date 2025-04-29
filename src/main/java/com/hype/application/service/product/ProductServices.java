package com.hype.application.service.product;


import com.hype.application.domain.ProductCollection.Collection;
import com.hype.application.dto.product.ProductResponseDTO;
import com.hype.application.domain.Product.Product;
import com.hype.application.domain.ProductImage.ProductImage;

import com.hype.application.domain.ProductVariation.ProductVariation;
import com.hype.application.domain.ProductVariation.VariationSize.VariationSize;
import com.hype.application.exception.EventNotFoundException;
import com.hype.application.repository.product.ProductRepository;
import com.hype.application.service.collection.ProductCollectionServices;
import com.hype.application.service.public_files.PublicFilesServices;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Getter
public class ProductServices {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCollectionServices productCollectionServices;

    @Autowired
    private ProductVariationServices productVariationServices;

    @Autowired
    private PublicFilesServices publicFilesServices;

    public void addProduct(Product product) {
        this.productRepository.save(product);
    }

    public Product AddImageToProduct(String ProductID, MultipartFile image) {
        Product product = getProduct(ProductID);

        ProductImage productImage = new ProductImage();
        String url = publicFilesServices.UploadFile(image);
        productImage.setUrl(url);
        productImage.setProduct(product);
        product.getImages().add(productImage);

        this.productRepository.save(product);
        return product;
    }

    public Product deleteImageFromProduct(String productId, String imageId) {
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


    public List<ProductResponseDTO> getAllProducts() {
        return this.productRepository.findAll()
                .stream()
                .map(ProductResponseDTO::new)
                .toList();
    }

    public void deleteProduct(String ProductID) {
        //Throws not found exception in case of not found
        Product product = getProduct(ProductID);

        this.productRepository.deleteById(ProductID);
    }

    public void deleteAllProducts() {
        //Throws not found exception in case of not found
        this.productRepository.deleteAll();
    }

    public void addVariation(String ID, ProductVariation variation){
        Product product = getProduct(ID);

        variation.setProduct(product);
        product.getVariations().add(variation);

        for (VariationSize size : variation.getSizes()) {
            size.setVariation(variation);
        }

        this.productVariationServices.getProductVariationRepository().save(variation);
    }

    public List<ProductVariation> getVariation(){
        return this.getProductVariationServices().getProductVariationRepository().findAll();
    }

    public void removeVariation(String ID, String VariationID){
        Product product = getProduct(ID);

        boolean removed = product.getVariations().removeIf(variation -> variation.getId().equals(VariationID));

        if (!removed) {
            throw new EventNotFoundException();
        }

        this.productRepository.save(product);
    }

    public void AddCollection(String ID, String CollectionID) {
        Product product = getProduct(ID);

        Collection collection = productCollectionServices.GetCollection(CollectionID);

        product.setProduct_collection(collection);

        this.productRepository.save(product);
    }

    public void RemoveCollection(String ID) {
        Product product = getProduct(ID);

        product.setProduct_collection(null);

        this.productRepository.save(product);
    }
}
