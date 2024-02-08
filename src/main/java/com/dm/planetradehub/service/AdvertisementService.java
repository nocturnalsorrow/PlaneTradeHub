package com.dm.planetradehub.service;

import com.dm.planetradehub.entity.Advertisement;
import com.dm.planetradehub.entity.Gallery;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface AdvertisementService {
    List<Advertisement> getAllAdvertisements();

    List<Advertisement> findAdvertisementsByParameters(Advertisement advertisement);

    List<Advertisement> findAdvertisementsBy(String type, String manufacturer, String model, int year);

    Advertisement getAdvertisementById(Long id);

    Advertisement addAdvertisement(Advertisement advertisement, List<MultipartFile> imageFiles) throws IOException;

    Advertisement saveAdvertisement(Advertisement advertisement);

    void deleteAdvertisementById(Long id);

    Optional<Gallery> getAdvertisementImage(Long advertisementId, Long imageId);
}
