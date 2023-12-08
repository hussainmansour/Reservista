package Reservista.example.Backend.Models.EntityClasses;

import Reservista.example.Backend.Models.IDClasses.BlockedUserId;
import Reservista.example.Backend.Validators.Gmail;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "blocked_user")
@IdClass(BlockedUserId.class)
public class BlockedUser {

    @Id
    @Column(name = "user_name")
    private String userName;

    @Id
    @Gmail
    @Column(name = "email" , unique = true)
    private String email;
}
