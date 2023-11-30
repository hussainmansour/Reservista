package Reservista.example.Backend.DAOs;


import Reservista.example.Backend.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    void setIsValidatedBoolean(String email);

    @Query("SELECT u.isValidated FROM User u WHERE u.email =:email")
    boolean findIsValidatedByEmail(String email);



}
