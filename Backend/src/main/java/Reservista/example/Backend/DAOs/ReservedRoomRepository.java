package Reservista.example.Backend.DAOs;

import Reservista.example.Backend.Models.EntityClasses.ReservedRoom;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.HashSet;
import java.util.UUID;
@Repository
public interface ReservedRoomRepository extends JpaRepository<ReservedRoom, UUID> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT r.roomNumber FROM ReservedRoom r WHERE r.roomDescription.id = :roomDescId "+
            "AND r.reservation.checkOut >= :checkIn AND r.reservation.checkIn <= :checkOut")
    HashSet<Integer> getNumberOfConflictedRooms(@Param("roomDescId") UUID roomDescId, @Param("checkIn") Instant checkIn, @Param("checkOut") Instant checkOut);

}
