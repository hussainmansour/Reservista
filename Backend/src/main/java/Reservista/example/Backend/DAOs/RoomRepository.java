package Reservista.example.Backend.DAOs;

import Reservista.example.Backend.Models.EntityClasses.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room, UUID> {
}
