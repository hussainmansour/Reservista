package Reservista.example.Backend.DAOs;


import Reservista.example.Backend.Models.EntityClasses.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, UUID> {
    boolean findHaveFullyRefundOptionById(UUID id);
    double findAdditionalRefundPercentageById(UUID id);
    Optional<Hotel> findById(UUID id);
}
