package Reservista.example.Backend.DAOs;


import Reservista.example.Backend.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {


    boolean existsByEmail(String email);

    boolean existsByUserName(String userName);


    boolean findIsValidatedByEmail(String email);

    Optional<User> findByEmail(String email);

}

