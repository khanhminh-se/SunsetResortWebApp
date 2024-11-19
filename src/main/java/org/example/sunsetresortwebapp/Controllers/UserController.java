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
                       model.addAttribute("user", user);
                        return "homepage";
                }else{
                        return "signin";
                }

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
        public String processSignUp(@RequestParam String email, @RequestParam String password, @RequestParam  String confirmPassword, @RequestParam  String fullname, @RequestParam  String  address, @RequestParam String phoneNumber , Model model){
                if(!confirmPassword.equalsIgnoreCase(password)){
                        model.addAttribute("error", "Passwords do not match");
                        return "signup";
                }else{
                        User user = new User();
                        user.setEmail(email);
                        user.setPassword(password);
                        user.setPhoneNumber(phoneNumber);
                        user.setAddress(address);
                        user.setFullname(fullname);
                       CheckUserResponse response =  userService.registerUser(user);
                       if(response.isSuccess()){
                                model.addAttribute("message", response.getMessage());
                       }else{
                               model.addAttribute("error", response.getMessage());
                       }
                       return "signup";
                }
        }

}
