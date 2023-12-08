package Reservista.example.Backend.Models.IDClasses;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportId implements Serializable {
    private String user;
    private String admin;
    private UUID review;
}
