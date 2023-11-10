package christmas.exception.non_fatal;

public abstract class CustomException extends IllegalArgumentException {
    public CustomException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
