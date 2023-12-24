package Reservista.example.Backend.Models;
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
@Table(name = "report")
@IdClass(ReportId.class)
public class Report {

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_name", referencedColumnName = "user_name")
    private User user;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "admin_name", referencedColumnName = "admin_name")
    private Admin admin;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "review_id", referencedColumnName = "id")
    private Review review;
}