package com.hype.application.service.product;


import com.hype.application.repository.product.ProductVariationRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Getter
public class ProductVariationServices {
    @Autowired
    ProductVariationRepository productVariationRepository;





}
