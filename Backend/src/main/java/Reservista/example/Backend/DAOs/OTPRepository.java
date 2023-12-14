package Reservista.example.Backend.DAOs;


import Reservista.example.Backend.Models.EntityClasses.OTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OTPRepository extends JpaRepository<OTP, String> {
    OTP findByEmail(String email);
}
