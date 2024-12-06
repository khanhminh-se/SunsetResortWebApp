package org.example.sunsetresortwebapp.Services;

import org.example.sunsetresortwebapp.Models.Accommodation;
import org.example.sunsetresortwebapp.Repository.AccommodationRepository;
import org.example.sunsetresortwebapp.Repository.AccommodationReservationDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccommodationReservationDetailService {
    private final AccommodationReservationDetailRepository accommodationReservationDetailRepository;
    private final AccommodationRepository accommodationRepository;
    @Autowired
    public AccommodationReservationDetailService(AccommodationReservationDetailRepository accommodationReservationDetailRepository, AccommodationRepository accommodationRepository) {
        this.accommodationReservationDetailRepository = accommodationReservationDetailRepository;
        this.accommodationRepository = accommodationRepository;
    }

    public int getReservedAvailability(Long accommodationId, LocalDate checkInDate, LocalDate checkOutDate) {
        Accommodation accommodation = accommodationRepository.findById(accommodationId).get();
        int reservedQuantity = accommodationReservationDetailRepository.getReservedQuantity(accommodationId, checkInDate, checkOutDate);
        System.out.println(reservedQuantity);
        System.out.println(accommodation.getAvailableQuantity());
        return accommodation.getAvailableQuantity() - reservedQuantity;
    }

    public Map<Long,Integer> getReservedAvailabilityForAllAccommodations(List<Accommodation> accommodations, LocalDate checkInDate, LocalDate checkOutDate){
        Map<Long,Integer> availabilityMap = new HashMap<>();
        for(Accommodation accommodation : accommodations){
            int reservedQuantity = accommodationReservationDetailRepository.getReservedQuantity(accommodation.getAccommodationId(), checkInDate, checkOutDate);
            availabilityMap.put(accommodation.getAccommodationId(), accommodation.getAvailableQuantity() - reservedQuantity);
        }
        return availabilityMap;
    }
}
