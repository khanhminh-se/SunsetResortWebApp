package org.example.sunsetresortwebapp.Controllers;

import jakarta.servlet.http.HttpSession;
import org.example.sunsetresortwebapp.Models.CheckUserResponse;
import org.example.sunsetresortwebapp.Models.User;
import org.example.sunsetresortwebapp.Repository.UserRepository;
import org.example.sunsetresortwebapp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
        private final UserService userService;
        private final UserRepository userRepository;
        @Autowired
        public UserController(UserService userService, UserRepository userRepository) {
                this.userService = userService;
                this.userRepository = userRepository;
        }
        @GetMapping("/signup")
        public String register() {
            return "signup";
        }

        @GetMapping("/signin")
        public String signin() {
                return "signin";
        }
        @GetMapping("/logout")
        public String logout(HttpSession session) {
                session.invalidate();
                return "redirect:/signin";
        }
        @GetMapping("/homepage")
        public String homepage(HttpSession session, Model model) {
                User user = (User) session.getAttribute("loggedInUser");
                if(user != null){
                        session.setAttribute("loggedInUsers", user);
                       model.addAttribute("user", user);
                        return "homepage";
                }else{
                        return "signin";
                }

        }


        @GetMapping("/bookingvilla")
        public String getBookingVilla() {
                return "accommodationdetail";
        }

        @GetMapping("/showproduct")
        public String getProduct() {
                return "accommodation";
        }

        @GetMapping("/reservable-service")
        public String getReservableService() {
                return "reserveservice";
        }

        @GetMapping("/reservable-booking")
        public String getReservableBooking() {
                return "restaurantbooking";
        }

        @GetMapping("/requestable-booking")
        public String getRequestBooking() {
                return "requestbooking";
        }

        @GetMapping("/requestable-service")
        public String getRequestService() {
                return "requestservice";
        }

        @GetMapping("/requestable-payment")
        public String getRequestBookingPayment() {
                return "paymentforrequest";
        }

        @GetMapping("/admin-dashboard")
        public String getAdminDashboard() {
                return "admindashboard";
        }

        @GetMapping("/general-service")
        public String getGeneralService() {
                return "generalservice";
        }

        @GetMapping("/payment")
        public String getAccommodationPayment() {
                return "paymentforaccommodation";
        }

        @PostMapping("/signin")
        public String processSignIn(@RequestParam String email, @RequestParam String password , Model model, HttpSession session){
                CheckUserResponse response = userService.checkUser(email, password);
                if(response.isSuccess()){
                        session.setMaxInactiveInterval(86400);
                        session.setAttribute("loggedInUser", userRepository.findUserByEmail(email));
                        return "redirect:/homepage";
                }else{
                        model.addAttribute("error", response.getMessage());
                        return "signin";
                }
        }

        @PostMapping("/signup")
        public String processSignUp(@RequestParam String email, @RequestParam String password, @RequestParam  String confirmPassword, Model model){
                CheckUserResponse response = userService.registerUser(email, password, confirmPassword);
                model.addAttribute("response", response);
                return "signup";
        }

}
