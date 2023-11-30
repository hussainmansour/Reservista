package Reservista.example.Backend.Models;

import Reservista.example.Backend.Enums.SystemRoles;
import Reservista.example.Backend.Enums.Gender;
import Reservista.example.Backend.Validators.Gmail;
import Reservista.example.Backend.Validators.ValidBirthDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.common.aliasing.qual.Unique;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User implements UserDetails {

    @Id
    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Gmail
    @NotBlank
    @Unique
    @Column(name = "email")
    private String email;

//    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @ValidBirthDate
    @Column(name = "birth_date")
    private LocalDate birthDate;


    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull
    @Column(name = "is_validated")
    private boolean isValidated;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    @ManyToMany(mappedBy = "users")
    private List<Notification> notifications;

    @ManyToMany(mappedBy = "users")
    private List<Voucher> vouchers;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(SystemRoles.USER.name()));
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isValidated;
    }
}
