package Reservista.example.Backend.Models.EntityClasses;

import Reservista.example.Backend.Models.IDClasses.ReportId;
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
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_name", referencedColumnName = "user_name", nullable = false)
    private User user;

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "admin_name", referencedColumnName = "admin_name", nullable = false)
    private Admin admin;

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "review_id", referencedColumnName = "id", nullable = false)
    private Review review;
}
