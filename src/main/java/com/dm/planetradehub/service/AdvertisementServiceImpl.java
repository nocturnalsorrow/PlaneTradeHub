package com.dm.planetradehub.service;

import com.dm.planetradehub.entity.Advertisement;
import com.dm.planetradehub.entity.Gallery;
import com.dm.planetradehub.repository.AdvertisementRepository;
import com.dm.planetradehub.repository.AircraftRepository;
import com.dm.planetradehub.repository.GalleryRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class AdvertisementServiceImpl implements AdvertisementService{
    private final AdvertisementRepository advertisementRepository;
    private final AircraftRepository aircraftRepository;
    private final GalleryRepository galleryRepository;

    public AdvertisementServiceImpl(AdvertisementRepository advertisementRepository, AircraftRepository aircraftRepository, GalleryRepository galleryRepository) {
        this.advertisementRepository = advertisementRepository;
        this.aircraftRepository = aircraftRepository;
        this.galleryRepository = galleryRepository;
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
    public Advertisement addAdvertisement(Advertisement advertisement, List<MultipartFile> imageFiles) throws IOException {
        List<Gallery> advertisementImages = new ArrayList<>();

        for (MultipartFile file : imageFiles) {
            Gallery advertisementImage = new Gallery();
            advertisementImage.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
            advertisementImage.setAdvertisement(advertisement);
            advertisementImages.add(advertisementImage);
        }
        aircraftRepository.save(advertisement.getAircraft());


        advertisement.setImages(advertisementImages);
        return advertisementRepository.save(advertisement);
    }

    @Override
    public Advertisement saveAdvertisement(Advertisement advertisement) {
        return advertisementRepository.save(advertisement);
    }

    @Override
    public void deleteAdvertisementById(Long id) {
        advertisementRepository.deleteById(id);
    }

    public Optional<Gallery> getAdvertisementImage(Long advertisementId, Long imageId) {
        return galleryRepository.findByAdvertisement_IdAndId(advertisementId, imageId);
    }
}
