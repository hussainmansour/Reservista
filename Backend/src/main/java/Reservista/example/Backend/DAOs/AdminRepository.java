package Reservista.example.Backend.DAOs;


import Reservista.example.Backend.Models.Admin;
import Reservista.example.Backend.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin,String> {

}
