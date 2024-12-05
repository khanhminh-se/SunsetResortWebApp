package org.example.sunsetresortwebapp.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "reservation_units")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationUnit {
    @Id
    @GeneratedValue
    private Long reservationUnitId;


    @OneToOne
    @JoinColumn(name ="accommodationUnit")
    private AccommodationUnit accommodationUnit;
}
