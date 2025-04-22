package com.hype.application.domain.ProductCollection.CollectionImage;

import com.hype.application.domain.ProductCollection.Collection;
import com.hype.application.domain.Image.ImageEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_collection_image")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CollectionImage extends ImageEntity {

    @ManyToOne
    @JoinColumn(name = "product_collection_id")
    private Collection collection;

    public CollectionImage(String URL, Collection collection) {
        super(URL);
        this.collection = collection;
    }
}
