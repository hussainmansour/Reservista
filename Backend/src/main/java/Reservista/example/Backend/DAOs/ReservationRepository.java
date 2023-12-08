package Reservista.example.Backend.DAOs;

import Reservista.example.Backend.Models.EntityClasses.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Repository
public interface ReservationRepository {
    @Query("SELECT rr.room.id FROM RoomReservationRelationship rr JOIN Reservation res " +
            "ON rr.reservation.id = res.id " +
            "WHERE (res.checkOut <= :checkin OR res.checkIn >= :checkout) " +
            "AND rr.room.id IN (:roomIds)")
    List<Long> findRoomIdsByReservationDate(@Param("checkin") Instant checkin, @Param("checkout") Instant checkout, @Param("roomIds") List<UUID> roomsId);

}
