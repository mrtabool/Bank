package org.example.exception;

public class InsufficientFundsException extends RuntimeException{

    public InsufficientFundsException() {
        super("There is not enough money in the account. Balance is: ");
    }
}
