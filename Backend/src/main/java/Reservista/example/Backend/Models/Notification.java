package Reservista.example.Backend.Models;

import Reservista.example.Backend.Enums.NotificationType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "content")
    private String content;

    @NotNull
    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;

    @NotNull
    @Column(name = "type")
    private NotificationType type;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_notification",
            joinColumns = @JoinColumn(name = "notification_id" , referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "username" , referencedColumnName = "username")
    )
    private List<User> users;
}
