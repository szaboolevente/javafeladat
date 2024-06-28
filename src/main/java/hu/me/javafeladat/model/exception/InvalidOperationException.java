package hu.me.javafeladat.model.exception;

public class InvalidOperationException extends RuntimeException {
    public InvalidOperationException(String message) {
        super(message);
    }

    public InvalidOperationException(String message, Exception e) {
        super(message, e);
    }
}
