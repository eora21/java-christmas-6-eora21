package christmas.exception;

public class CustomException extends IllegalArgumentException {
    public CustomException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
