package com.dm.planetradehub.controller;

import com.dm.planetradehub.entity.Advertisement;
import com.dm.planetradehub.service.AdvertisementService;
import com.dm.planetradehub.service.AircraftService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdvertisementController {
    private final AdvertisementService advertisementService;
    private final AircraftService aircraftService;

    public AdvertisementController(AdvertisementService advertisementService, AircraftService aircraftService, ManufacturerRepository manufacturerRepository) {
        this.advertisementService = advertisementService;
        this.aircraftService = aircraftService;
        this.manufacturerRepository = manufacturerRepository;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        Advertisement advertisement = new Advertisement();
        model.addAttribute("advertisement", advertisement);
        model.addAttribute("advertisements", advertisementService.getAllAdvertisements());

        return "index";
    }

    @PostMapping("/advertisements")
    public String advertisements(@ModelAttribute Advertisement advertisement, Model model) {
        model.addAttribute("advertisements", advertisementService.findAdvertisementsByParameters(advertisement));

        return "advertisements";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable("id") Long id, Model model) {
        model.addAttribute("advertisement", advertisementService.getAdvertisementById(id));

        return "details";
    }
}
