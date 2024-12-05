package org.example.sunsetresortwebapp.DTO;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public record AccommodationReservationDTO(
        LocalDate checkInDate, LocalDate checkOutDate, int totalQuantity, double totalPrice, int reservedQuantity, Long userId, Long accommodationId
) {
}
