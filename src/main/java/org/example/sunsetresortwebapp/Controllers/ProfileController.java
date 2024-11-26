package org.example.sunsetresortwebapp.Controllers;

import jakarta.servlet.http.HttpSession;
import org.example.sunsetresortwebapp.Models.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {
    @GetMapping("/profile")
    public String getProfile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if(user != null){
            session.setAttribute("loggedInUsers", user);
            model.addAttribute("user", user);
            return "profile";
        }else{
            return "redirect:/signin";
        }
    }
}
