package Reservista.example.Backend.DAOs;

import Reservista.example.Backend.Models.EntityClasses.RoomDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoomDescriptionRepository extends JpaRepository<RoomDescription, UUID> {

    @Query("SELECT desc.roomCount FROM RoomDescription desc WHERE desc.id = :id")
    int findNumberOfRoomsByRoomDescriptionId(@Param("id") UUID id);

    Optional<RoomDescription> findRoomDescriptionById(UUID id);
 
}
