package org.example.sunsetresortwebapp.Controllers;


import jakarta.servlet.http.HttpSession;
import org.example.sunsetresortwebapp.DTO.AccommodationReservationDetailDTO;
import org.example.sunsetresortwebapp.Enum.UserRole;
import org.example.sunsetresortwebapp.Models.*;
import org.example.sunsetresortwebapp.Services.AccommodationReservationService;
import org.example.sunsetresortwebapp.Services.RequestableServiceRequestService;
import org.example.sunsetresortwebapp.Services.ReservableServiceReservationService;
import org.example.sunsetresortwebapp.Services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class AdminController {
    private final UserService userService;
    private final AccommodationReservationService accommodationReservationService;
    private final ReservableServiceReservationService reservableServiceReservationService;
    private final RequestableServiceRequestService requestableServiceRequestService;

    public AdminController(UserService userService, AccommodationReservationService accommodationReservationService, ReservableServiceReservationService reservableServiceReservationService, RequestableServiceRequestService requestableServiceRequestService) {
        this.userService = userService;
        this.accommodationReservationService = accommodationReservationService;
        this.reservableServiceReservationService = reservableServiceReservationService;
        this.requestableServiceRequestService = requestableServiceRequestService;
    }

    @GetMapping("/admindashboard")
    public String getAdmindashboard(Model model, HttpSession session) {
        List<User>  users = userService.getAllUsers().stream().filter((user) -> user.getUserRole().equals(UserRole.USER)).collect(Collectors.toList());
        List<AccommodationReservation> accommodationReservations = accommodationReservationService.getAllAccommodationReservations();
        List<ReservableServiceReservation> reservableServiceReservations = reservableServiceReservationService.findAll();
        List<RequestableServiceRequest> requestableServiceRequests = requestableServiceRequestService.getAllRequestableServiceRequests();
        model.addAttribute("admin", session.getAttribute("loggedInUser"));
        model.addAttribute("users", users);
        model.addAttribute("accommodationReservations", accommodationReservations);
        model.addAttribute("reservableServiceReservations", reservableServiceReservations);
        model.addAttribute("requestableServiceRequests", requestableServiceRequests);
        return "admindashboard";
    }
    @GetMapping("/accommodation-reservation-details/{accommodation-reservation-id}")
    public ResponseEntity<Map<String,Object>>  getAllAccommodationReservationDetails(@PathVariable("accommodation-reservation-id") Long accommodationReservationId) {
        Map<String,Object> response = new HashMap<>();
        List<AccommodationReservationDetail> details = accommodationReservationService.getAccommodationReservation(accommodationReservationId).getAccommodationReservationDetails();

        List<AccommodationReservationDetailDTO> dtoList = details.stream().map(detail -> new AccommodationReservationDetailDTO(
                        detail.getAccommodationReservationDetailId(),
                        detail.getReservedQuantity(),
                        detail.getAccommodationReservationTotalPrice(), detail.getAccommodation().getAccommodationName()))
                .collect(Collectors.toList());
        response.put("status", "success");
        response.put("details", dtoList);
        return ResponseEntity.ok(response);
    }
}
