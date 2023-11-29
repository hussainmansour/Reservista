package Reservista.example.Backend.Errors;

import Reservista.example.Backend.DTOs.RegistrationResponseDTO;

public class CredentialsException extends Exception{

    public CredentialsException(String message){
        super(message);
    }

}
