package Reservista.example.Backend.DAOs;

import Reservista.example.Backend.Models.EntityClasses.Room;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room, UUID> {


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT r.id From Room r where r.hotel.id = :hotelId")
    List<Long> findRoomIdsForUpdate(@Param("hotelId") String hotelId);

    @Query("SELECT r.id FROM Room r WHERE r.roomDescription.title = :typeId AND r.id IN (:roomIds)")
    List<Long> findRoomIdsByType(@Param("typeId") String typeId, @Param("roomIds") List<Long> roomIds);


}
