package br.gabrielsmartins.smartpayment.application.exception;

public class FraudNotFoundException extends RuntimeException {

    public FraudNotFoundException(String message){
        super(message);
    }
}
