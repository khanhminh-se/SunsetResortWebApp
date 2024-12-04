package org.example.sunsetresortwebapp.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.sunsetresortwebapp.Enum.ReservationStatus;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue
    private Long reservationId;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @ManyToOne
    @JoinColumn(name ="user_id")
    private User user;

   @OneToMany(
           mappedBy = "reservation",
           cascade = CascadeType.ALL,
           fetch = FetchType.EAGER
   )
    List<ReservationDetail> reservationDetails;


    public Reservation() {}


}
