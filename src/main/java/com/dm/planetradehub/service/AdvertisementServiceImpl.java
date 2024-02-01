package com.dm.planetradehub.service;

import com.dm.planetradehub.entity.Advertisement;
import com.dm.planetradehub.entity.Type;
import com.dm.planetradehub.repository.AdvertisementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdvertisementServiceImpl implements AdvertisementService{
    private final AdvertisementRepository advertisementRepository;

    public AdvertisementServiceImpl(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }

    @Override
    public List<Advertisement> getAllAdvertisements() {
        return advertisementRepository.findAll();
    }

    @Override
    public List<Advertisement> findAdvertisementsByParameters(Advertisement advertisement){
        return advertisementRepository.findAdvertisementsByParameters(advertisement.getAircraft().getType().getName(),
                advertisement.getAircraft().getManufacturer().getName(),
                advertisement.getAircraft().getModel().getName(),
                advertisement.getAircraft().getYear());
    }
    @Override
    public List<Advertisement> findAdvertisementsBy(String type, String manufacturer, String model, int year){
        return advertisementRepository.findAdvertisementsByParameters(type, manufacturer, model, year);
    }

    @Override
    public Advertisement getAdvertisementById(Long id) { return advertisementRepository.findAdvertisementById(id); }

    @Override
    public Advertisement saveAdvertisement(Advertisement advertisement) {
        return advertisementRepository.save(advertisement);
    }

    @Override
    public void deleteAdvertisementById(Long id) {
        advertisementRepository.deleteById(id);
    }
}
