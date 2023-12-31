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

    public AdvertisementController(AdvertisementService advertisementService, AircraftService aircraftService) {
        this.advertisementService = advertisementService;
        this.aircraftService = aircraftService;
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

    @GetMapping("/{manufacturer}{model}{year}")
    public String details(@PathVariable("manufacturer") String manufacturer, @PathVariable("model") String advert_model, @PathVariable("year") String year, Model model) {
        model.addAttribute("advertisement", advertisementService.getAdvertisementById(9L));

        return "details";
    }
}
