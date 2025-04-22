package com.hype.application.service.type;

import com.hype.application.domain.Product.Product;
import com.hype.application.dto.type.ProductTypeResponseDTO;
import com.hype.application.domain.ProductType.ProductType;
import com.hype.application.domain.ProductType.ProductTypeImage.ProductTypeImage;
import com.hype.application.exception.EventNotFoundException;
import com.hype.application.repository.product_type.ProductTypeRepository;

import com.hype.application.service.public_files.PublicFilesServices;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


@Service
@Getter
public class ProductTypeServices {

    @Autowired
    ProductTypeRepository  productTypeRepository;

    @Autowired
    PublicFilesServices publicFilesServices;

    private ProductType TryGetProductType(String ID){
        Optional<ProductType> optionalProductType = productTypeRepository.findById(ID);

        if(optionalProductType.isEmpty())
            throw new EventNotFoundException();

        return optionalProductType.get();
    }

    public List<ProductTypeResponseDTO> GetAllProductTypes(){
        return productTypeRepository.findAll().stream().map(ProductTypeResponseDTO::new).toList();
    }

    public void AddProductType(ProductType productType)
    {
        productTypeRepository.save(productType);
    }

    public ProductType GetProductType(String ID){
      return TryGetProductType(ID);
    }

    public void UpdateProductType(String ID, ProductType productType ){

        ProductType productType1 = TryGetProductType(ID);
        productType1.setName(productType.getName());
        productType1.setImages(productType.getImages());
        productType1.setProducts(productType.getProducts());

        productTypeRepository.save(productType1);
    }

    public void DeleteProductType(String ID){
        TryGetProductType(ID);
        productTypeRepository.deleteById(ID);
    }
    public ProductType AddImageToProductType(String ID, MultipartFile file)
    {
        ProductType productType = TryGetProductType(ID);
        String url = publicFilesServices.UploadFile(file);

        ProductTypeImage image = new ProductTypeImage(url, productType);
        System.out.print(url);

        productType.getImages().add(image);

        productTypeRepository.save(productType);

        return productType;
    }



    public ProductType RemoveImageFromProductType(String ID, String ImageID)
    {
        ProductType productType = TryGetProductType(ID);

        productType.getImages().removeIf(image -> image.getId().equals(ImageID));

        productTypeRepository.save(productType);
        return productType;
    }

    public List<Product> GetProductsOnType(String ID){
        ProductType productType = TryGetProductType(ID);
        return productType.getProducts();
    }

}
