package christmas.exception.fatal;

import christmas.exception.CustomException;

public abstract class FatalException extends CustomException {
    public FatalException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
