package Reservista.example.Backend.DAOs;


import Reservista.example.Backend.Models.EntityClasses.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,String> {
    boolean existsByAdminName(String adminName);
}
