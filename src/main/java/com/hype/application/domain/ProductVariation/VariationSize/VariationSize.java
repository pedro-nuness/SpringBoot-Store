package com.hype.application.domain.ProductVariation.VariationSize;

import com.hype.application.domain.ProductVariation.ProductVariation;
import com.hype.application.dto.variation.variationsize.VariationSizeRequestDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "variation_size")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class VariationSize {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String size;     // Ex: "P", "M", "G", "39", etc.
    private Integer stock;   // Quantidade em estoque

    @ManyToOne
    @JoinColumn(name = "variation_id")
    private ProductVariation variation;


    public VariationSize(VariationSizeRequestDTO dto){
        this.size = dto.size();
        this.stock = dto.stock();
    }
}