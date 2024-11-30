package org.example.sunsetresortwebapp.Models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name ="services")
@Inheritance(strategy = InheritanceType.JOINED)
public class ResortService {
    @Id
    @GeneratedValue
    private Long serviceId;
    @Column(name ="price")
    private Double price;
    @Column(name ="description")
    private String description;

    @Column(name ="openTime")
    private LocalDateTime openTime;
    private LocalDateTime closeTime;


    @OneToMany(
            mappedBy = "resortService",
            cascade = CascadeType.ALL
    )
    List<Image> images;


    public ResortService(Double price, String description, LocalDateTime openTime, LocalDateTime closeTime, List<Image> images) {
        this.price = price;
        this.description = description;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.images = images;
    }
    public ResortService() {}

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(LocalDateTime openTime) {
        this.openTime = openTime;
    }

    public LocalDateTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(LocalDateTime closeTime) {
        this.closeTime = closeTime;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
