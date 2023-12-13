package Reservista.example.Backend.DAOs;

import Reservista.example.Backend.Models.EntityClasses.ReservedRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReservedRoomRepository extends JpaRepository<ReservedRoom, UUID> {
}
