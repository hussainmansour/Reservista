package Reservista.example.Backend.Controllers;

import Reservista.example.Backend.DAOs.*;
import Reservista.example.Backend.DTOs.InvoiceComponent.ReservationDTO;
import Reservista.example.Backend.DTOs.Registration.RegistrationRequestDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import Reservista.example.Backend.Enums.StatusCode;
import Reservista.example.Backend.Error.DeactivatedAccountException;
import Reservista.example.Backend.Error.RegistrationCredentialsException;
import Reservista.example.Backend.Models.EntityClasses.*;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/test")
public class ReservationController {


    @Autowired
    UserRepository userRepository;

    @Autowired
    RoomDescriptionRepository roomDescriptionRepository;

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    ReservedRoomRepository reservedRoomRepository;

    @Autowired
    TempReservationDetailsRepository tempReservationDetailsRepository;

    @PostMapping("/confirm")
    public ResponseDTO<String> confirm(@Valid @RequestBody ReservationDTO request) {

        return null;
    }




    @PostMapping("/addRes")
    public void addRes() {
        System.out.println("PRINT");
        RoomDescription roomDesc = roomDescriptionRepository.findById(UUID.fromString("0002d331-89d7-4f19-b6bf-8cf553b767c5")).orElse(null);
        User user = userRepository.findByEmail("marwanesam24322@gmail.com").orElse(null);
        Hotel hotel = hotelRepository.findById(UUID.fromString("001cc902-bea3-4381-86af-4064e3b90fc8")).orElse(null);
        TempReservationDetails t = new TempReservationDetails();
        Set<ReservedRoom> rooms = new HashSet<>();
        rooms.add(new ReservedRoom());
        rooms.add(new ReservedRoom());
        rooms.add(new ReservedRoom());
        Reservation res = Reservation.builder().price(100).isConfirmed(true).checkIn(Instant.now()).checkOut(Instant.now().plus(Duration.ofDays(4))).voucherApplied(false).paymentIntentId("HERO").isConfirmed(true).isRefundable(true).roomDescription(roomDesc).user(user).hotel(hotel).reservedRooms(rooms).tempReservationDetails(t).build();

        for (ReservedRoom r : rooms) {
            r.setReservation(res);
            r.setRoomDescription(roomDesc);
//            reservedRoomRepository.save(r);
        }


        t.setReservation(res);
        t.setInvoice("HEROOOOKEOIP");
        user.getReservations().add(res);
        hotel.getReservations().add(res);
        roomDesc.getReservedRooms().addAll(rooms);
        roomDesc.getReservations().add(res);
//        userRepository.save(user);
//        hotelRepository.save(hotel);
//        tempReservationDetailsRepository.save(t);
//        roomDescriptionRepository.save(roomDesc);
        reservationRepository.save(res);
    }


//    @PostMapping("/cancel")
//    public ResponseDTO<String> cancelReservation(@Valid @RequestBody String reservationId) {
//
//        return null;
//    }

}
