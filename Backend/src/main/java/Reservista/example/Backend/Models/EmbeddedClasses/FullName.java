package Reservista.example.Backend.Models.EmbeddedClasses;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FullName {
    private String firstName;
    private String middleName;
    private String lastName;
}
