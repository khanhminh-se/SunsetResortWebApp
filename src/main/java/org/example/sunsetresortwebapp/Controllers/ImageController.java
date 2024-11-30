package org.example.sunsetresortwebapp.Controllers;

import org.example.sunsetresortwebapp.DTO.ImageDTO;
import org.example.sunsetresortwebapp.Models.Image;
import org.example.sunsetresortwebapp.Services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ImageController {
    private final ImageService imageService;
    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }
    @GetMapping("/images")
    public List<Image> getImages() {
        return imageService.getAllImages();
    }
    @PostMapping("/images/")
    public String addImage(@RequestBody ImageDTO imageDTO, Model model) {
       Image image  = imageService.createImage(imageDTO.imageUrl(),imageDTO.accommodationId(), null, "Accommodation");
        model.addAttribute("image", image);
        return "accommodation";
    }
}
