package com.dm.planetradehub.controller;

import com.dm.planetradehub.entity.Aircraft;
import com.dm.planetradehub.repository.AdvertisementRepository;
import com.dm.planetradehub.repository.AircraftRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdvertisementController {
    private final AdvertisementRepository advertisementRepository;
    private final AircraftRepository aircraftRepository;

    public AdvertisementController(AdvertisementRepository advertisementRepository, AircraftRepository aircraftRepository) {
        this.advertisementRepository = advertisementRepository;
        this.aircraftRepository = aircraftRepository;
    }

    @GetMapping("/")
    public String homePage(Model model){
        Aircraft aircraft = new Aircraft();
        model.addAttribute("aircraft", aircraft);

        return "index";
    }

    @GetMapping("/aircrafts")
    public String aircrafts(@ModelAttribute Aircraft aircraft, Model model){
        model.addAttribute("aircrafts", aircraftRepository.findAircraftsByParameters("Helicopter", "Daewoo", "Matiz"));

        return "index";
    }
}
