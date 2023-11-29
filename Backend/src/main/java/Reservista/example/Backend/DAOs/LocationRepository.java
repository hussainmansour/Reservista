package Reservista.example.Backend.DAOs;

import Reservista.example.Backend.Models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LocationRepository extends JpaRepository<Location, UUID> {

}
