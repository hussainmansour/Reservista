package Reservista.example.Backend.DAOs;


import Reservista.example.Backend.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    boolean existsByEmail(String email);

    boolean existsByUserName(String userName);

    boolean FindIsValidByEmail(String email);
}
