package Reservista.example.Backend.DAOs;

import Reservista.example.Backend.Models.EntityClasses.RoomImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoomImageRepository extends JpaRepository<RoomImage, UUID> {
}
