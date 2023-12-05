package Reservista.example.Backend.Error;

public class DeactivatedAccountException extends Exception{

    public DeactivatedAccountException (String message){
        super(message);
    }
}
