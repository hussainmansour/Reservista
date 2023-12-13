package Reservista.example.Backend.DAOs;

import Reservista.example.Backend.Models.EntityClasses.RoomDescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoomDescriptionRepository extends JpaRepository<RoomDescription, UUID> {
}
