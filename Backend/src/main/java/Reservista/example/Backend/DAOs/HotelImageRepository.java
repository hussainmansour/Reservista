package Reservista.example.Backend.DAOs;

import Reservista.example.Backend.Models.EntityClasses.HotelImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HotelImageRepository extends JpaRepository<HotelImage, UUID> {
}
