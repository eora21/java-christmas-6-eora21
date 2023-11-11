package christmas.exception;

public abstract class CustomException extends IllegalArgumentException {
    public CustomException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
