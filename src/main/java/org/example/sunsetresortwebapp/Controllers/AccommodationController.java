package org.example.sunsetresortwebapp.Controllers;

import org.example.sunsetresortwebapp.DTO.AccommodationReservationDTO;
import org.example.sunsetresortwebapp.DTO.AccommodationSearchingDTO;
import org.example.sunsetresortwebapp.Services.AccommodationReservationDetailService;
import org.example.sunsetresortwebapp.Services.AccommodationReservationService;
import org.springframework.ui.Model;
import org.example.sunsetresortwebapp.Models.Accommodation;
import org.example.sunsetresortwebapp.Services.AccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AccommodationController {
    private final AccommodationService accommodationService;
    private final AccommodationReservationDetailService accommodationReservationDetailService;
    private final AccommodationReservationService accommodationReservationService;
    @Autowired
    public AccommodationController(AccommodationService accommodationService, AccommodationReservationDetailService accommodationReservationDetailService, AccommodationReservationService accommodationReservationService) {
        this.accommodationService = accommodationService;
        this.accommodationReservationDetailService = accommodationReservationDetailService;
        this.accommodationReservationService = accommodationReservationService;
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
    @GetMapping("/accommodations/detail-booking/{accommodation-id}")
    public String getAccommodationDetails(@PathVariable("accommodation-id") Long accommodationId, Model model) {
        Accommodation accommodation = accommodationService.getAccommodationById(accommodationId);
        model.addAttribute("accommodation", accommodation);
        return "accommodationdetail";
    }

    @GetMapping("/accommodations/search/{accommodation-id}/{check-in-date}/{check-out-date}")
    public String searchAccommodation(@PathVariable("accommodation-id") Long accommodationId,@PathVariable("check-in-date") String checkInDate, @PathVariable("check-out-date") String checkOutDate, Model model) {
            Accommodation accommodation = accommodationService.getAccommodationById(accommodationId);
            model.addAttribute("accommodation", accommodation);
            Map<Long, Integer> availabilityMap = new HashMap<>();
            int availability = accommodationReservationDetailService.getReservedAvailability(accommodationId, LocalDate.parse(checkInDate),  LocalDate.parse(checkOutDate));
        System.out.println(accommodationId);
            System.out.println(availability);

            availabilityMap.put(accommodationId, availability);
             model.addAttribute("availabilityMap", availabilityMap);
             return "accommodationfiltering";
    }
    @PostMapping("/accommodations/searchAccommodation")
    public String searchAccommodation(@RequestBody AccommodationSearchingDTO accommodationSearchingDTO , Model model) {
        System.out.println(accommodationSearchingDTO.checkInDate());
        System.out.println(accommodationSearchingDTO.checkOutDate());

       List<Accommodation> filteredAccommodations = accommodationService.filterAccommodationWithNumberOfGuests(accommodationSearchingDTO.numberOfGuests());
       if(filteredAccommodations.size()  > 0){
            Map<Long,Integer>  availabilityMap = accommodationReservationDetailService.getReservedAvailabilityForAllAccommodations(filteredAccommodations,LocalDate.parse(accommodationSearchingDTO.checkInDate()), LocalDate.parse(accommodationSearchingDTO.checkOutDate()));
            model.addAttribute("accommodations", filteredAccommodations);
            model.addAttribute("availabilityMap", availabilityMap);
       }else{
           model.addAttribute("error", "No Accommodation has that number of guests , please try another !");
       }
        return "accommodation";
    }


    @PostMapping("/accommodations/makereservation")
    public String makeAccommodationReservation(@RequestBody AccommodationReservationDTO accommodationReservationDTO, Model model) {
        model.addAttribute("message", "Succesfully make a reservation");
        accommodationReservationService.makeReservation(accommodationReservationDTO);
        return "accommodationfiltering";
    }




}
