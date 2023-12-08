package Reservista.example.Backend.Models.EmbeddedClasses;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomImage {

    @Lob
    @NotNull
    private byte[] source;
}
