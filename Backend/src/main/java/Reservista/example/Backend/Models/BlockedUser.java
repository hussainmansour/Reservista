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
import org.checkerframework.common.aliasing.qual.Unique;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "blocked_user")
public class BlockedUser {

    @Id
    @Column(name = "user_name")
    private String userName;

    @Unique
    @Gmail
    @Column(name = "email")
    private String email;
}
