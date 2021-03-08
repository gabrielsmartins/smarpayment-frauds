package br.gabrielsmartins.smartpayment.frauds.application.exception;

public class FraudNotFoundException extends RuntimeException {

    public FraudNotFoundException(String message){
        super(message);
    }
}
