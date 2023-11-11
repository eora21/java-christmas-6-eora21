package christmas.exception.recoverable;

import christmas.exception.CustomException;

public abstract class RecoverableException extends CustomException {
    public RecoverableException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
