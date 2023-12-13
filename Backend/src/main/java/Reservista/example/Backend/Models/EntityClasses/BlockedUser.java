package Reservista.example.Backend.Models.EntityClasses;

import Reservista.example.Backend.Validators.Gmail;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "blocked_user")
public class BlockedUser {

    @Id
    @Column(name = "user_name")
    private String userName;


    @Gmail
    @Column(name = "email" , unique = true)
    private String email;
}
