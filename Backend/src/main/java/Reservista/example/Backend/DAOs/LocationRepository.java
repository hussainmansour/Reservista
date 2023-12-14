package Reservista.example.Backend.DAOs;

import Reservista.example.Backend.Models.EntityClasses.Location;
import Reservista.example.Backend.Models.IDClasses.LocationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface LocationRepository extends JpaRepository<Location, LocationId> {

}
