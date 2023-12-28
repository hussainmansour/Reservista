package Reservista.example.Backend.Services.Cancellation;

import Reservista.example.Backend.Error.GlobalException;

public abstract class CancellationHandler {

    protected CancellationHandler nextHandler;

    public abstract long handleRequest(CancellationRequest cancellationRequest) throws GlobalException;

    public void setNextHandler(CancellationHandler reservationHandler){
        if(reservationHandler != null)
            this.nextHandler = reservationHandler;
    }

}

