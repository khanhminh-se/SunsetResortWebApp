package org.example.sunsetresortwebapp.Controllers;

import jakarta.servlet.http.HttpSession;
import org.example.sunsetresortwebapp.Models.User;
import org.example.sunsetresortwebapp.Models.UserProfile;
import org.example.sunsetresortwebapp.Services.UserProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ProfileController {
    private final UserProfileService userProfileService;
    public ProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }
    @GetMapping("/profile")
    public String getProfile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        UserProfile userProfile = userProfileService.findUserProfileById(user.getId());
        if(user != null){
            session.setAttribute("loggedInUsers", user);
            model.addAttribute("userProfile", userProfile);
            model.addAttribute("user", user);
            System.out.println("OKKKKK");
            return "profile";
        }else{
            return "redirect:/signin";
        }
    }
}
