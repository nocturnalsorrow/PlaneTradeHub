package com.dm.planetradehub.controller;

import com.dm.planetradehub.entity.Aircraft;
import com.dm.planetradehub.repository.AdvertisementRepository;
import com.dm.planetradehub.repository.AircraftRepository;
import com.dm.planetradehub.service.AdvertisementService;
import com.dm.planetradehub.service.AircraftService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String homePage(Model model){
        Aircraft aircraft = new Aircraft();
        model.addAttribute("aircraft", aircraft);
        model.addAttribute("aircrafts", aircraftService.getAllAircrafts());

        return "index";
    }

    @PostMapping("/")
    public String aircrafts(@ModelAttribute Aircraft aircraft, Model model){
        model.addAttribute("aircrafts", aircraftService.findAircraftsByParameters(aircraft));

        return "index";
    }
}
