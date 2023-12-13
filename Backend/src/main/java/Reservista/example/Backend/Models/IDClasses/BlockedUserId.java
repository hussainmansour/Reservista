package Reservista.example.Backend.Models.IDClasses;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlockedUserId implements Serializable {
    private String userName;
    private String email;
}
