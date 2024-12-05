package org.example.sunsetresortwebapp.Repository;

import org.example.sunsetresortwebapp.Models.AccommodationReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationReservationRepository extends JpaRepository<AccommodationReservation, Long> {
}
