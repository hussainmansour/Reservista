package Reservista.example.Backend.DAOs;

import Reservista.example.Backend.Models.EntityClasses.BlockedUser;
import Reservista.example.Backend.Models.IDClasses.BlockedUserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlockedUserRepository extends JpaRepository<BlockedUser, BlockedUserId> {


    boolean existsByEmail(String email);

    Optional<BlockedUser> findByEmail(String email);

}
