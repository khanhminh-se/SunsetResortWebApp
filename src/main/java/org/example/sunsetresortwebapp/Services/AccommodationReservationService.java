package org.example.sunsetresortwebapp.Services;

import org.example.sunsetresortwebapp.DTO.AccommodationReservationDTO;
import org.example.sunsetresortwebapp.Models.Accommodation;
import org.example.sunsetresortwebapp.Models.AccommodationReservation;
import org.example.sunsetresortwebapp.Models.AccommodationReservationDetail;
import org.example.sunsetresortwebapp.Models.User;
import org.example.sunsetresortwebapp.Repository.AccommodationRepository;
import org.example.sunsetresortwebapp.Repository.AccommodationReservationDetailRepository;
import org.example.sunsetresortwebapp.Repository.AccommodationReservationRepository;
import org.example.sunsetresortwebapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccommodationReservationService {
    private final AccommodationReservationRepository accommodationReservationRepository;
    private final UserRepository userRepository;
    private final AccommodationRepository accommodationRepository;
    private final AccommodationReservationDetailRepository accommodationReservationDetailRepository;

    @Autowired
    public AccommodationReservationService(AccommodationReservationRepository accommodationReservationRepository, UserRepository userRepository, AccommodationRepository accommodationRepository, AccommodationReservationDetailRepository accommodationReservationDetailRepository) {
        this.accommodationReservationRepository = accommodationReservationRepository;
        this.userRepository = userRepository;
        this.accommodationRepository = accommodationRepository;
        this.accommodationReservationDetailRepository = accommodationReservationDetailRepository;
    }

    public void makeReservation(AccommodationReservationDTO accommodationReservationDTO) {
        User user = userRepository.findUserById(accommodationReservationDTO.userId());
        Accommodation accommodation = accommodationRepository.findById(accommodationReservationDTO.accommodationId()).get();
        AccommodationReservation accommodationReservation = new AccommodationReservation();
        accommodationReservation.setUser(user);
        accommodationReservation.setCheckInDate(accommodationReservationDTO.checkInDate());
        accommodationReservation.setCheckOutDate(accommodationReservationDTO.checkOutDate());
        accommodationReservation.setTotalPrice(accommodationReservationDTO.totalPrice());
        accommodationReservation.setTotalQuantity(accommodationReservationDTO.totalQuantity());
        AccommodationReservationDetail accommodationReservationDetail = new AccommodationReservationDetail();
        accommodationReservationDetail.setAccommodation(accommodation);
        accommodationReservationDetail.setReservedQuantity(accommodationReservationDTO.reservedQuantity());
        accommodationReservationDetail.setAccommodationReservation(accommodationReservation);
        accommodationReservationRepository.save(accommodationReservation);
        accommodationReservationDetailRepository.save(accommodationReservationDetail);
    }

}
