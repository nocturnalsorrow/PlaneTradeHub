package com.dm.planetradehub.controller;

import com.dm.planetradehub.entity.Advertisement;
import com.dm.planetradehub.repository.AdvertisementRepository;
import com.dm.planetradehub.repository.ManufacturerRepository;
import com.dm.planetradehub.repository.ModelRepository;
import com.dm.planetradehub.repository.TypeRepository;
import com.dm.planetradehub.service.AdvertisementService;
import com.dm.planetradehub.service.AircraftService;
import com.dm.planetradehub.service.ModelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
