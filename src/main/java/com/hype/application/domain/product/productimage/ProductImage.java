package com.hype.application.domain.product.productimage;



import com.hype.application.domain.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Table(name = "product_image")
@Entity(name = "product_image")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID  )
    private String id;

    private String url;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public ProductImage(ProductImageRequestDTO productImageRequestDTO) {
        this.url = productImageRequestDTO.URL();
    }

}
