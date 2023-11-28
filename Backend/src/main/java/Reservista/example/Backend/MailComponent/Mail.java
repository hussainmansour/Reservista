package Reservista.example.Backend.MailComponent;

import lombok.*;
//

@Data
@Setter
@Getter
public class Mail {
    private String body;
    private String subject;
    private String to;

}
