package Reservista.example.Backend.DAOs;

import Reservista.example.Backend.Models.EntityClasses.BlockedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlockedUserRepository extends JpaRepository<BlockedUser,String> {


    boolean existsByEmail(String email);

    Optional<BlockedUser> findByEmail(String email);

}
