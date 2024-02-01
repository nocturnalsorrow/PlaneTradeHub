package com.dm.planetradehub.service;

import com.dm.planetradehub.entity.Advertisement;
import java.util.List;
import java.util.Optional;

public interface AdvertisementService {
    List<Advertisement> getAllAdvertisements();

    List<Advertisement> findAdvertisementsByParameters(Advertisement advertisement);

    List<Advertisement> findAdvertisementsBy(String type, String manufacturer, String model, int year);

    Advertisement getAdvertisementById(Long id);

    Advertisement saveAdvertisement(Advertisement advertisement);

    void deleteAdvertisementById(Long id);
}
