package Reservista.example.Backend.Services.Cancellation;

import Reservista.example.Backend.DAOs.HotelRepository;
import Reservista.example.Backend.DAOs.ReservationRepository;
import Reservista.example.Backend.Models.EntityClasses.Reservation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.NoSuchElementException;

@SpringBootTest
class RefundCalculatorHandlerTest {




    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    HotelRepository hotelRepository;

    @Test
    public void testNotFOUND() {

                    List<Object[]> reservationDetails = reservationRepository.findEmailFirstNameReservationIdByPaymentIntentId("pi_3ORd98IpHzJgrvA90J5VR10q").orElseThrow(() -> new NoSuchElementException("This reservation was not found"));


            Object[] reservationTempDetails = reservationDetails.get(0);
            String email = (String) reservationTempDetails[0];
            String firstName = (String) reservationTempDetails[1];
            Long reservationId = (Long) reservationTempDetails[2];

//        System.out.println(reservationId);
        String reservationID = "001cc902-bea3-4381-86af-4064e3b90fc8";
        Reservation reservation = reservationRepository.findByIdAndUserUserName(reservationId,"mariam").orElseThrow();
        System.out.println(reservation.getHotel().getFullyRefundableRate());
        System.out.println(reservationRepository.isUsernameMatchingReservation("mariamm", reservationId));

        List<Object[]> reservationDetails2 = reservationRepository.findEmailHotelNameFirstNameByReservationId(reservationId).orElseThrow(() -> new NoSuchElementException("This reservation was not found"));

        System.out.println(reservationDetails2.toString());
        Object[] reservationTempDetails2 = reservationDetails2.get(0);
        String email2 = (String) reservationTempDetails2[0];
        String firstName2 = (String) reservationTempDetails2[1];
//        Long reservationId2 = (Long) reservationTempDetails2[2];
        String hotelName = (String) reservationTempDetails2[2];

//        String location = (String) reservationTempDetails2[4];
        System.out.println(email2);
        System.out.println(firstName2);
        System.out.println(hotelName);

//         Hotel hotel = reservation.getHotel();
//         hotel.setFullyRefundableRate(15);
//
//         hotelRepository.save(hotel);


//        System.out.println(reservation.getHotel().getFullyRefundableRate());
        int fullyRefundableRate = reservationRepository.findFullRefundableRateByReservationId(reservationId);
        System.out.println(fullyRefundableRate);

    }

}