package com.dm.planetradehub.controller;

import com.dm.planetradehub.entity.Advertisement;
import com.dm.planetradehub.entity.Aircraft;
import com.dm.planetradehub.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class ProfileController {
    private final AdvertisementService advertisementService;
    private final TypeService typeService;
    private final ManufacturerService manufacturerService;
    private final ModelService modelService;
    private final AircraftService aircraftService;


    public ProfileController(AdvertisementService advertisementService, TypeService typeService, ManufacturerService manufacturerService, ModelService modelService, AircraftService aircraftService) {
        this.advertisementService = advertisementService;
        this.typeService = typeService;
        this.manufacturerService = manufacturerService;
        this.modelService = modelService;
        this.aircraftService = aircraftService;
    }

    @GetMapping("/profile")
    public String profile(){
        return "profile";
    }

    @GetMapping("/myAdvertisements")
    public String myAdvertisements(Model model, Authentication authentication){
        model.addAttribute("advertisements", advertisementService.getMyAdvertisements(authentication));

        return "myAdvertisements";
    }

    @GetMapping("/myAdvertisement/{id}")
    public String updateMyAdvertisement(@PathVariable Long id, Model model){
        model.addAttribute("advertisement", advertisementService.getAdvertisementById(id));
        model.addAttribute("types", typeService.getAllTypes());
        model.addAttribute("manufacturers", manufacturerService.getAllManufacturers());
        model.addAttribute("models", modelService.getAllModels());
        model.addAttribute("aircraft", advertisementService.getAdvertisementById(id).getAircraft());

        return "updateAdvertisement";
    }

    @PostMapping("/myAdvertisement")
    public String updateMyAdvertisement(@ModelAttribute Advertisement advertisement,
                                        @ModelAttribute Aircraft aircraft,
                                        @RequestParam("imageFiles") List<MultipartFile> imageFiles,
                                        Authentication authentication) throws IOException {
        advertisementService.updateAdvertisement(advertisement, imageFiles, authentication);
        return "redirect:/myAdvertisements";
    }

    @GetMapping("/myAdvertisements/{id}")
    public String deleteMyAdvertisement(@PathVariable Long id){
        advertisementService.deleteAdvertisementById(id);

        return "redirect:/myAdvertisements";
    }

    @GetMapping("/advertisement")
    public String addAdvertisement(Model model) {
        model.addAttribute("types", typeService.getAllTypes());
        model.addAttribute("manufacturers", manufacturerService.getAllManufacturers());
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
        return "redirect:/myAdvertisements";
    }
}
