package app.linguistai.exception;

public class NotFoundException extends Exception {
    public NotFoundException() {
        super("ERROR: NotFoundException");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
