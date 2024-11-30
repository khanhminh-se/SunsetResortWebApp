package org.example.sunsetresortwebapp.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name ="accommodation")
public class Accommodation {
    @Id
    @GeneratedValue
    private Long accommodationId;
    @Column(name="type")
    private String accommodationType;
    @Column(name ="quantity")
    private Integer availableQuantity;
    @Column(name ="price")
    private Double pricePerNight;
    private String description;
    private String amenities;
    private int maximumGuests;

    @OneToMany(
            mappedBy = "accommodation",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    List<Image> images;

    public Accommodation(List<Image> images, int maximumGuests, String amenities, String description, Double pricePerNight, Integer availableQuantity, String accommodationType) {
        this.images = images;
        this.maximumGuests = maximumGuests;
        this.amenities = amenities;
        this.description = description;
        this.pricePerNight = pricePerNight;
        this.availableQuantity = availableQuantity;
        this.accommodationType = accommodationType;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaximumGuests() {
        return maximumGuests;
    }

    public void setMaximumGuests(int maximumGuests) {
        this.maximumGuests = maximumGuests;
    }

    public Accommodation() {}

    public Long getAccommodationId() {
        return accommodationId;
    }

    public void setAccommodationId(Long accommodationId) {
        this.accommodationId = accommodationId;
    }

    public String getAccommodationType() {
        return accommodationType;
    }

    public void setAccommodationType(String accommodationType) {
        this.accommodationType = accommodationType;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public Double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(Double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }



    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
