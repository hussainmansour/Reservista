package Reservista.example.Backend.DAOs;


import Reservista.example.Backend.Models.EntityClasses.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminRepository extends JpaRepository<Admin,String> {
    boolean existsByAdminName(String adminName);


}
