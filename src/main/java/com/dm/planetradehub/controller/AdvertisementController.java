package com.dm.planetradehub.controller;

import com.dm.planetradehub.entity.Advertisement;
import com.dm.planetradehub.entity.Aircraft;
import com.dm.planetradehub.entity.Gallery;
import com.dm.planetradehub.repository.AdvertisementRepository;
import com.dm.planetradehub.repository.ManufacturerRepository;
import com.dm.planetradehub.repository.ModelRepository;
import com.dm.planetradehub.repository.TypeRepository;
import com.dm.planetradehub.service.AdvertisementService;
import com.dm.planetradehub.service.AircraftService;
import com.dm.planetradehub.service.ModelService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Controller
public class AdvertisementController {
    private final AdvertisementService advertisementService;
    private final AircraftService aircraftService;
    private final TypeRepository typeRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final ModelService modelService;
    private final AdvertisementRepository advertisementRepository;



    public AdvertisementController(AdvertisementService advertisementService, AircraftService aircraftService, TypeRepository typeRepository, ManufacturerRepository manufacturerRepository, ModelService modelService, AdvertisementRepository advertisementRepository) {
        this.advertisementService = advertisementService;
        this.aircraftService = aircraftService;
        this.typeRepository = typeRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.modelService = modelService;
        this.advertisementRepository = advertisementRepository;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("types", typeRepository.findAll());
        model.addAttribute("manufacturers", manufacturerRepository.findAll());
        model.addAttribute("models", modelService.getAllModels());
        model.addAttribute("advertisements", advertisementService.getAllAdvertisements());

        return "index";
    }

    @GetMapping("/advertisement")
    public String addAdvertisement(Model model) {
        model.addAttribute("types", typeRepository.findAll());
        model.addAttribute("manufacturers", manufacturerRepository.findAll());
        model.addAttribute("models", modelService.getAllModels());
        model.addAttribute("advertisement", new Advertisement());
        model.addAttribute("aircraft", new Aircraft());
        return "createAdvertisement";
    }

    @PostMapping("/advertisement")
    public String addProduct(@ModelAttribute Advertisement advertisement,
                             @ModelAttribute Aircraft aircraft,
                             @RequestParam("imageFiles") List<MultipartFile> imageFiles,
                             Authentication authentication)
            throws IOException {
        advertisementService.addAdvertisement(advertisement, aircraft, imageFiles, authentication);
        return "redirect:/";
    }

    @GetMapping("/{advertisementId}/image/{imageId}")
    public ResponseEntity<byte[]> getAdvertisementImage(@PathVariable Long advertisementId,
                                                        @PathVariable Long imageId) {
        Optional<Gallery> advertisementImageOptional = advertisementService.getAdvertisementImage(advertisementId, imageId);

        if (advertisementImageOptional.isPresent()) {
            Gallery advertisementImage = advertisementImageOptional.get();
            byte[] imageBytes = Base64.getDecoder().decode(advertisementImage.getImage());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new byte[0], HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/advertisements")
    public String advertisements(@RequestParam(value = "type", required = false) String type,
                                 @RequestParam(value = "manufacturer", required = false) String manufacturer,
                                 @RequestParam(value = "model", required = false) String modelOfAircraft,
                                 Model model) {
        model.addAttribute("type", type);
        model.addAttribute("manufacturer", manufacturer);
        model.addAttribute("model", modelOfAircraft);
        model.addAttribute("types", typeRepository.findAll());
        model.addAttribute("manufacturers", manufacturerRepository.findAll());
        model.addAttribute("models", modelService.getModelsByManufacturer(manufacturer));
        model.addAttribute("advertisements", advertisementService.findAdvertisementsBy(type, manufacturer, modelOfAircraft, 0));

        return "advertisements";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable("id") Long id, Model model) {
        model.addAttribute("advertisement", advertisementService.getAdvertisementById(id));

        return "details";
    }
}
