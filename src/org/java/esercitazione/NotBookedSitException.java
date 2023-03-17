package org.java.esercitazione;

public class NotBookedSitException extends RuntimeException{
    public NotBookedSitException(String message) {
        super(message);
    }
}
