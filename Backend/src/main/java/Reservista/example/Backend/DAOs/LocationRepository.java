package Reservista.example.Backend.DAOs;

import Reservista.example.Backend.Models.EntityClasses.Location;
import Reservista.example.Backend.Models.IDClasses.LocationId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LocationRepository extends JpaRepository<Location, LocationId> {

}
