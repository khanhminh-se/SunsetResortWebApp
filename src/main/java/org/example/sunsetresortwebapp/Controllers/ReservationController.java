package org.example.sunsetresortwebapp.Controllers;

import org.example.sunsetresortwebapp.DTO.BookingDTO;
import org.example.sunsetresortwebapp.DTO.BookingDTORequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ReservationController {
    @PostMapping("/reservations/make-reservations")
    public ResponseEntity<Map<String,Object>> makeReservation(@RequestBody BookingDTORequest bookingDTORequest, Model model, RedirectAttributes redirectAttributes) {
        bookingDTORequest.getBookings().forEach((bookingDTO -> {
            System.out.println(bookingDTO.toString());
        }));
        Map<String,Object> response = new HashMap<>();
        response.put("bookings", bookingDTORequest.getBookings());
        response.put("redirectURL" , "/payment-accommodations");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/payment-accommodations")
    public String payment(Model model, RedirectAttributes redirectAttributes) {
        if(redirectAttributes.containsAttribute("bookings")) {
            System.out.println("exists bookings");
        }else{
            System.out.println("does not exist bookings");
        }
        return "paymentforaccommodation";
    }
}
