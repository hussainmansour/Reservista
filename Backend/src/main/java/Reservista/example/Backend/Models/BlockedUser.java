package Reservista.example.Backend.Models;

import Reservista.example.Backend.Validators.Gmail;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BlockedUser")
public class BlockedUser {

    @Id
    @Column(name = "username")
    private String username;

    @Gmail
    @Column(name = "email")
    private String email;
}
