package christmas.exception.alert;

import christmas.exception.CustomException;

public abstract class AlertException extends CustomException {
    public AlertException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
