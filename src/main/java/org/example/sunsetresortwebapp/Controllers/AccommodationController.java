package org.example.sunsetresortwebapp.Controllers;

import org.springframework.ui.Model;
import org.example.sunsetresortwebapp.Models.Accommodation;
import org.example.sunsetresortwebapp.Services.AccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class AccommodationController {
    private final AccommodationService accommodationService;
    @Autowired
    public AccommodationController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }
    @GetMapping("/accommodations")
    public String getAllAccommodations(Model model) {
        List<Accommodation>  accommodations=  accommodationService.getAllAccommodations();
        model.addAttribute("accommodations", accommodations);
        return "accommodation";

    }
    @PostMapping("/accommodations")
    public String createAccommodation( @RequestBody  Accommodation accommodation , Model model ) {
           Accommodation accommodation1 =  accommodationService.createAccommodation(accommodation);
        model.addAttribute("accommodation", accommodation1);
           return "accommodation";
    }
}
