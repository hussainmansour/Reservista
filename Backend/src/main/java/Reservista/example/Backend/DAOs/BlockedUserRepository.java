package Reservista.example.Backend.DAOs;

import Reservista.example.Backend.Models.BlockedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlockedUserRepository extends JpaRepository<BlockedUser,String> {
    Optional<BlockedUser> findByEmail(String email);
}
