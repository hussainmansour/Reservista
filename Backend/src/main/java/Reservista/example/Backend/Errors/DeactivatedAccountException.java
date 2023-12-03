package Reservista.example.Backend.Errors;

public class DeactivatedAccountException extends Exception{

    public DeactivatedAccountException (String message){
        super(message);
    }
}
