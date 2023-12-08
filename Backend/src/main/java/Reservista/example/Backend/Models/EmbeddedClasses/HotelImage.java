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
public class HotelImage {

    @Lob
    @NotNull
    @Column(length = 2_147_483_647)
    private byte[] source;

    private String caption;
}
