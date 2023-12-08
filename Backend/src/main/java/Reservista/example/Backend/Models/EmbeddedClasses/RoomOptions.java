package Reservista.example.Backend.Models.EmbeddedClasses;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class RoomOptions {
    private boolean breakfast;
    private boolean lunch;
    private boolean dinner;

    // todo: should this be a rate or percentage ??
    @Min(0)
    @Max(100)
    @NotNull
    private int refundableRate;
}
