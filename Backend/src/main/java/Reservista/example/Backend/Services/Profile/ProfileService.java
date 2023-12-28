package Reservista.example.Backend.Services.Profile;

import Reservista.example.Backend.DAOs.ReservationRepository;
import Reservista.example.Backend.DAOs.UserRepository;
import Reservista.example.Backend.DTOs.Profile.ProfileDTO;
import Reservista.example.Backend.DTOs.Profile.UpcomingHistoryReservationDTO;
import Reservista.example.Backend.DTOs.Profile.UpdateDTO;
import Reservista.example.Backend.Enums.ErrorCode;
import Reservista.example.Backend.Error.GlobalException;
import Reservista.example.Backend.Models.EmbeddedClasses.FullName;
import Reservista.example.Backend.Models.EntityClasses.Reservation;
import Reservista.example.Backend.Models.EntityClasses.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationRepository reservationRepository;


    public ProfileDTO viewProfile(String username) throws GlobalException {

        User user = userRepository.findById(username).orElseThrow(()->new GlobalException(ErrorCode.PROFILE_NOT_FOUND, HttpStatus.NOT_FOUND)); // 404
        return ProfileDTO.builder()
                .userName(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFullName().getFirstName())
                .middleName(user.getFullName().getMiddleName())
                .lastName(user.getFullName().getLastName())
                .birthDate(user.getBirthDate())
                .gender(user.getGender())
                .nationality(user.getNationality())
                .build();
    }

    public void updateProfile(String username, UpdateDTO updateDTO) throws GlobalException {
        User user = userRepository.findById(username).orElseThrow(()->new GlobalException(ErrorCode.PROFILE_NOT_FOUND,HttpStatus.NOT_FOUND));
        user.setFullName(FullName.builder()
                .firstName(updateDTO.getFirstName())
                .middleName(updateDTO.getMiddleName())
                .lastName(updateDTO.getLastName())
                .build());
        user.setBirthDate(updateDTO.getBirthDate());
        user.setGender(updateDTO.getGender());
        user.setNationality(updateDTO.getNationality());
        userRepository.save(user);

    }

    public List<UpcomingHistoryReservationDTO> getUpcomingReservations(String username) throws GlobalException {
        List<Reservation> reservations = reservationRepository.findUpcomingReservationsByUserName(username, Instant.now()).orElseThrow(()->new GlobalException(ErrorCode.UPCOMING_RESERVATIONS_NOT_FOUND,HttpStatus.NOT_FOUND));
        return convertToReservationDTO(reservations);
    }

    public List<UpcomingHistoryReservationDTO> getHistoryReservations(String username) throws GlobalException {
        List<Reservation> reservations = reservationRepository.findHistoryReservationsByUserName(username, Instant.now()).orElseThrow(()->new GlobalException(ErrorCode.HISTORY_RESERVATIONS_NOT_FOUND,HttpStatus.NOT_FOUND));
        return convertToReservationDTO(reservations);
    }

    // mapping from Reservation to UpcomingHistoryReservationDTO
    private List<UpcomingHistoryReservationDTO> convertToReservationDTO(List<Reservation> reservations) {
        List<UpcomingHistoryReservationDTO> reservationDTOS = new ArrayList<>();
        for (Reservation reservation : reservations) {
            reservationDTOS.add(UpcomingHistoryReservationDTO.builder()
                    .reservationID(reservation.getId())
                    .hotelName(reservation.getHotel().getName())
                    .roomTitle(reservation.getRoomDescription().getTitle())
                    .reservationDate(reservation.getReservationDate())
                    .checkIn(reservation.getCheckIn())
                    .checkOut(reservation.getCheckOut())
                    .price(reservation.getPrice())
                    .noOfRooms(reservation.getReservedRooms().size())
                    .invoice(reservation.getTempReservationDetails().getInvoice())
                    .build());
        }
        return reservationDTOS;
    }

}
