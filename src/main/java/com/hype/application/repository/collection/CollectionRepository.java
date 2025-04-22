package com.hype.application.repository.collection;

import com.hype.application.domain.ProductCollection.Collection;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CollectionRepository extends JpaRepository<Collection, String> {
}