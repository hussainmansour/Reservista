package Reservista.example.Backend.DAOs;

import Reservista.example.Backend.Models.EntityClasses.OptionalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OptionalServiceRepository extends JpaRepository<OptionalService,String> {

    @Query("SELECT o.price FROM OptionalService o  WHERE o.serviceName=:name AND o.hotel.id=: hotelID")
    double findPriceOption(@Param("hotelID") UUID hotelId,@Param("name") String name);

}
