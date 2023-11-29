package Reservista.example.Backend.DAOs;

import Reservista.example.Backend.Models.BlockedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockedUserRepository extends JpaRepository<BlockedUser,String> {

}
