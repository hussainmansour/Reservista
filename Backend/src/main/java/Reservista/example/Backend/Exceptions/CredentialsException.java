package Reservista.example.Backend.Exceptions;

import Reservista.example.Backend.DTOs.RegistrationResponseDTO;

public class CredentialsException extends Exception{

    public CredentialsException(String message){
        super(message);
    }

}
