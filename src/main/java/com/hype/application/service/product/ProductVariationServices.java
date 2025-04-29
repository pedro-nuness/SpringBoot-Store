package com.hype.application.service.product;


import com.hype.application.domain.Product.Product;
import com.hype.application.domain.ProductImage.ProductImage;
import com.hype.application.domain.ProductVariation.ProductVariation;
import com.hype.application.domain.ProductVariation.ProductVariationImage;
import com.hype.application.exception.EventNotFoundException;
import com.hype.application.repository.product.ProductVariationRepository;
import com.hype.application.service.public_files.PublicFilesServices;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


@Service
@Getter
public class ProductVariationServices {
    @Autowired
    ProductVariationRepository productVariationRepository;

    @Autowired
    PublicFilesServices publicFilesServices;

    public ProductVariation getProductVariationById(String id) {
        Optional<ProductVariation> productVariation = productVariationRepository.findById(id);

        if(productVariation.isEmpty()) {
            throw new EventNotFoundException();
        }

        return productVariation.get();
    }

    public ProductVariation addImage(String id, MultipartFile file) {
        ProductVariation variation = getProductVariationById(id);

        ProductVariationImage variationImage = new ProductVariationImage();
        String url = publicFilesServices.UploadFile(file);
        variationImage.setUrl(url);
        variationImage.setProductVariation(variation);
        variation.getImages().add(variationImage);

        this.productVariationRepository.save(variation);
        return variation;
    }

    public void deleteImage(String id, String imageId) {
        ProductVariation variation = getProductVariationById(id);

        boolean removed = variation.getImages().removeIf(image -> image.getId().equals(imageId));

        if (!removed) {
            throw new EventNotFoundException();
        }

        this.productVariationRepository.save(variation);
    }

    public List<ProductVariation> getAllProductVariations() {
        return this.productVariationRepository.findAll();
    }




}
