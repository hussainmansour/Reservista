package Reservista.example.Backend.Models.EntityClasses;

import Reservista.example.Backend.Enums.NotificationType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "content")
    private String content;

    @NotNull
    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;

    @NotNull
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private NotificationType type;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "user_notification",
            joinColumns = @JoinColumn(name = "notification_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_name", referencedColumnName = "user_name")
    )
    private Set<User> users;
}
