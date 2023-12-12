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
public class OptionalServiceId implements Serializable {
    private String serviceName;
    private UUID hotel;
}