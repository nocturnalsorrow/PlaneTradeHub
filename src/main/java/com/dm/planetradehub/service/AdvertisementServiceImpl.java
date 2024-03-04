package com.dm.planetradehub.service;

import com.dm.planetradehub.entity.Advertisement;
import com.dm.planetradehub.entity.Aircraft;
import com.dm.planetradehub.entity.Gallery;
import com.dm.planetradehub.entity.User;
import com.dm.planetradehub.repository.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class AdvertisementServiceImpl implements AdvertisementService{
    private final AdvertisementRepository advertisementRepository;
    private final GalleryRepository galleryRepository;
    private final UserService userService;


    public AdvertisementServiceImpl(AdvertisementRepository advertisementRepository, GalleryRepository galleryRepository, UserService userService) {
        this.advertisementRepository = advertisementRepository;
        this.galleryRepository = galleryRepository;
        this.userService = userService;
    }

    @Override
    public List<Advertisement> getAllAdvertisements() {
        return advertisementRepository.findAll();
    }

    @Override
    public List<Advertisement> getMyAdvertisements(Authentication authentication) {
        return advertisementRepository.findAdvertisementsByUser(userService.getUserByEmail(authentication.getName()));
    }

    @Override
    public List<Advertisement> findAdvertisementsByParameters(Advertisement advertisement){
        return advertisementRepository.findAdvertisementsByParameters(advertisement.getAircraft().getType(),
                advertisement.getAircraft().getManufacturer(),
                advertisement.getAircraft().getModel(),
                advertisement.getAircraft().getYear());
    }

    @Override
    public List<Advertisement> findAdvertisementsBy(String type, String manufacturer, String model, int year){
        return advertisementRepository.findAdvertisementsByParameters(type, manufacturer, model, year);
    }

    @Override
    public Advertisement getAdvertisementById(Long id) { return advertisementRepository.findAdvertisementById(id); }

    @Override
    public Advertisement addAdvertisement(Advertisement advertisement, Aircraft aircraft, List<MultipartFile> imageFiles, Authentication authentication) throws IOException {
        List<Gallery> advertisementImages = new ArrayList<>();

        for (MultipartFile file : imageFiles) {
            Gallery advertisementImage = new Gallery();
            advertisementImage.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
            advertisementImage.setAdvertisement(advertisement);
            advertisementRepository.save(advertisement);
        }
        advertisement.setAircraft(aircraft);
        advertisement.setUser(userService.getUserByEmail(authentication.getName()));
        advertisement.setImages(advertisementImages);
        advertisement.setPublicationDate(Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date())));
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
