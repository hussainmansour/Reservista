package Reservista.example.Backend.Services.Reservation;

import Reservista.example.Backend.DAOs.ReservationRepository;
import Reservista.example.Backend.DAOs.TempReservationDetailsRepository;
import Reservista.example.Backend.DTOs.Reservation.ReservationDTO;
import Reservista.example.Backend.DTOs.Response.ReservationResponseDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import Reservista.example.Backend.Models.EntityClasses.Reservation;
import Reservista.example.Backend.Models.EntityClasses.ReservedRoom;
import Reservista.example.Backend.Models.EntityClasses.TempReservationDetails;
import jakarta.transaction.Transactional;
import org.apache.tomcat.util.descriptor.web.JspConfigDescriptorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class DatabaseReservationHandler extends ReservationHandler{

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    TempReservationDetailsRepository tempReservationDetailsRepository;


    @Transactional
    public ResponseDTO<ReservationResponseDTO> handleRequest(ReservationDTO reservationDTO) {


        Reservation reservation =reservationRepository.findById(reservationDTO.getReservationID()).orElseThrow(() -> new NoSuchElementException("This reservation was not found"));

        Iterator<ReservedRoom> roomIterator = reservation.getReservedRooms().iterator();

        for (int i = 0; i < reservationDTO.getReservedRooms().size() && roomIterator.hasNext(); i++) {
            ReservedRoom reservedRoom = roomIterator.next();
            reservedRoom.setPrice(reservationDTO.getReservedRooms().get(i).getTotalPrice());
        }

        reservation.setPrice(reservationDTO.getFinalPrice());
        reservation.setPaymentIntentId(reservationDTO.getPaymentIntentId());

        TempReservationDetails tempReservationDetails = TempReservationDetails.builder().invoice(reservationDTO.getInvoice()).reservation(reservation).build();

        reservation.setTempReservationDetails(tempReservationDetails);
        tempReservationDetailsRepository.save(tempReservationDetails);
        reservationRepository.save(reservation);

        return nextHandler.handleRequest(reservationDTO);
    }

}
