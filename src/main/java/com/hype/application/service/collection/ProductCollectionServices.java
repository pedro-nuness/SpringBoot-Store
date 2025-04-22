package com.hype.application.service.collection;


import com.hype.application.domain.ProductCollection.Collection;
import com.hype.application.domain.ProductCollection.CollectionImage.CollectionImage;
import com.hype.application.exception.EventNotFoundException;
import com.hype.application.repository.collection.CollectionRepository;
import com.hype.application.service.public_files.PublicFilesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCollectionServices {

    @Autowired
    CollectionRepository collectionRepository;

    @Autowired
    PublicFilesServices publicFilesServices;


    public Collection TryGetCollection(String ID) {

        Optional<Collection> collection = collectionRepository.findById(ID);
        if(collection.isEmpty()) {
            throw new EventNotFoundException();
        }

        return collection.get();
    }

    public Collection GetCollection(String ID) {
        return TryGetCollection(ID);
    }

    public List<Collection> GetAllCollections() {
        return collectionRepository.findAll();
    }

    public Collection AddCollection(Collection collection) {
        return collectionRepository.save(collection);
    }

    public void RemoveCollection(String ID) {
         Collection collection = TryGetCollection(ID);
         collectionRepository.delete(collection);
    }

    public void AddImageToCollection(String ID, MultipartFile image) {

        Collection collection = TryGetCollection(ID);

        String url = publicFilesServices.UploadFile(image);

        CollectionImage collectionImage = new CollectionImage(url, collection );

        collection.getImages().add(collectionImage);

        collectionRepository.save(collection);
    }

    public void RemoveImageFromCollection(String ID, String ImageID) {
        Collection collection = TryGetCollection(ID);

        collection.getImages().removeIf(image -> image.getId().equals(ImageID));

        collectionRepository.save(collection);
    }
}
