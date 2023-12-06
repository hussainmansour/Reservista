package Reservista.example.Backend.DAOs;

import Reservista.example.Backend.Models.OTP;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OTPRepository extends JpaRepository<OTP, String> {
    OTP findByEmail(String email);
}
