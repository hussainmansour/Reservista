package Reservista.example.Backend.DAOs;

import Reservista.example.Backend.Models.EntityClasses.RoomDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;
import java.util.UUID;
@Repository
public interface RoomDescriptionRepository extends JpaRepository<RoomDescription, UUID> {
    Optional<RoomDescription> findById(UUID id);

}
