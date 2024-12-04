package org.example.sunsetresortwebapp.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name ="reservation_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDetail {
    @Id
    @GeneratedValue
    private Long reservationDetailId;
    @Column(name ="total_price")
    private double totalPrice;
    @Column(name ="quantity")
    private int totalQuantity;

    @Column(name ="length_of_stays")
    private int lengthOfStays;


    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;



    @OneToMany(
            mappedBy = "reservationDetail",
            cascade =  CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<ReservationUnit>  reservationUnits;

    @OneToOne
    @JoinColumn(name ="reservable_service_id")
    private ReservableResortService reservableService;

}
