package Reservista.example.Backend.DAOs;


import Reservista.example.Backend.Models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,String> {
    Admin findByAdminname(String adminName);
}
