package org.example.sunsetresortwebapp.Repository;

import org.example.sunsetresortwebapp.Enum.ReservationStatus;
import org.example.sunsetresortwebapp.Models.AccommodationReservationDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AccommodationReservationDetailRepository extends JpaRepository<AccommodationReservationDetail, Long> {
    @Query("SELECT COALESCE(SUM(d.reservedQuantity), 0) " +
            "FROM AccommodationReservationDetail d " +
            " JOIN d.accommodationReservation ar ON d.accommodationReservation.accommodationReservationId = ar.accommodationReservationId " +
            "WHERE d.accommodation.accommodationId = :accommodationId " +
            "AND  (:checkOutDate >= ar.checkInDate AND :checkInDate <= ar.checkOutDate)"
    )
    Integer getReservedQuantity(@Param("accommodationId") Long accommodationId,
                                @Param("checkInDate") LocalDate checkInDate,
                                @Param("checkOutDate") LocalDate checkOutDate
    );
}
