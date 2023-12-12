
package Reservista.example.Backend.Services.Reservation;

import Reservista.example.Backend.DAOs.*;
import Reservista.example.Backend.DTOs.Reservation.ReservationDTO;
import Reservista.example.Backend.DTOs.Reservation.ReservedRoomDTO;
import Reservista.example.Backend.DTOs.Response.ReservationResponseDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import Reservista.example.Backend.Enums.StatusCode;
import Reservista.example.Backend.Models.EmbeddedClasses.RoomFoodOptions;
import Reservista.example.Backend.Models.EntityClasses.Reservation;
import Reservista.example.Backend.Models.EntityClasses.ReservedRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class RoomAvailabilityHandler extends ReservationHandler {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoomDescriptionRepository roomDescriptionRepository;
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    ReservedRoomRepository reservedRoomRepository;
    @Autowired
    ReservationRepository reservationRepository;


    @Override
    public ResponseDTO<ReservationResponseDTO> handleRequest(ReservationDTO reservationDTO) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        int roomCount = roomDescriptionRepository.findNumberOfRoomsByRoomDescriptionId(reservationDTO.getRoomDescriptionId());
        Reservation reservation = prepareReservation(reservationDTO);

        reservationDTO.setReservationID(reservation.getId());
        try {
            checkAndReserve(reservation, reservationDTO.getRoomDescriptionId(), roomCount);
            executor.schedule(() -> deleteAfter10min(reservation.getId()), 10, TimeUnit.MINUTES);
            executor.shutdown();
            return nextHandler.handleRequest(reservationDTO);
        } catch (Exception e) {
            ResponseDTO<ReservationResponseDTO> responseDTO =new ResponseDTO<>(StatusCode.NOT_AVAILABLE.getCode(),StatusCode.NOT_AVAILABLE.getMessage(),null);
            return responseDTO;
        }

    }

    public Reservation prepareReservation(ReservationDTO reservationDTO) {
        Set<ReservedRoom> reservedRoomSet = new HashSet<ReservedRoom>();
        for (ReservedRoomDTO r : reservationDTO.getReservedRooms()) {
            ReservedRoom reservedRoom = ReservedRoom.builder()
                    .roomDescription(roomDescriptionRepository.findRoomDescriptionById(reservationDTO.getRoomDescriptionId()).orElseThrow())
                    .roomFoodOptions(new RoomFoodOptions(r.isHasBreakfast(), r.isHasLunch(), r.isHasDinner()))
                    .build();
            reservedRoomSet.add(reservedRoom);

        }

        return Reservation.builder()
                .user(userRepository.findByUserName(reservationDTO.getUserName()).orElseThrow())
                .hotel(hotelRepository.findById(reservationDTO.getHotelID()).orElseThrow())
                .checkIn(reservationDTO.getCheckIn())
                .checkOut(reservationDTO.getCheckOut())
                .reservedRooms(reservedRoomSet)
                .isRefundable(reservationDTO.isRefundable())
                .isConfirmed(false)
                .build();
    }

    @Transactional
    public void checkAndReserve(Reservation reservation, UUID roomDescriptionId, int roomCount) {
        int nonAvailableRooms = reservedRoomRepository.getNumberOfConflictedRooms(roomDescriptionId, reservation.getCheckIn(), reservation.getCheckOut());
        int availableRooms = roomCount - nonAvailableRooms;
        if (availableRooms >= reservation.getReservedRooms().size()) {
            reservationRepository.save(reservation);
        } else {
            throw new RuntimeException("Not enough available rooms for reservation.");
        }
    }

    public void deleteAfter10min(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow();
        if (!reservation.isConfirmed()) {
            reservationRepository.delete(reservation);
        }

    }

}