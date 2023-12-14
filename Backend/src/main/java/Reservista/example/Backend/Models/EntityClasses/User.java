package Reservista.example.Backend.Models.EntityClasses;

import Reservista.example.Backend.Enums.SystemRoles;
import Reservista.example.Backend.Enums.Gender;
import Reservista.example.Backend.Models.EmbeddedClasses.FullName;
import Reservista.example.Backend.Validators.Country;
import Reservista.example.Backend.Validators.Gmail;
import Reservista.example.Backend.Validators.BirthDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
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
    @Column(name = "email" , unique = true)
    private String email;

    @Embedded
    private FullName fullName;

    @BirthDate
    @Column(name = "birth_date")
    private LocalDate birthDate;

    // todo: add notnull
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Country
    @Column(name = "nationality")
    private String nationality;

    @Column(name = "is_activated")
    private boolean isActivated;

    @Column(name = "is_blocked")
    private boolean isBlocked;

    @Lob
    @Column(name = "profile_image" , columnDefinition = "LONGBLOB")
    private byte[] profileImage;


    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    private Set<Reservation> reservations;

    @ManyToMany(mappedBy = "users", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<Notification> notifications;

    @ManyToMany(mappedBy = "users", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<Voucher> vouchers;

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
        return isActivated;
    }
}
