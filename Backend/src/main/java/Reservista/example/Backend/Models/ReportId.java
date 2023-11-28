package Reservista.example.Backend.Models;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportId implements Serializable {
    private User user;
    private Admin admin;
    private Review review;
}
