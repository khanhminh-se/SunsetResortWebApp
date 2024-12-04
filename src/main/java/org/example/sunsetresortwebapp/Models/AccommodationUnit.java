package org.example.sunsetresortwebapp.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "accommodation_units")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationUnit {
    @Id
    @GeneratedValue
    private Long accommodationUnitId;
    @Column(name ="accommodation_unit_name", unique = true)
    private String accommodationUnitName;

    @ManyToOne
    @JoinColumn(name="accommodation_id")
    private Accommodation accommodation;


    @OneToOne(
            mappedBy = "accommodationUnit",
            cascade =  CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private ReservationUnit reservationUnit;

}
