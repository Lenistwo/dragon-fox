package pl.lenistwo.dragonfox.exceptions;

public class BadTokenException extends RuntimeException {

    public BadTokenException() {
    }

    public BadTokenException(String message) {
        super(message);
    }
}
