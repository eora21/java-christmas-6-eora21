package christmas.exception.recoverable;

public class MismatchOrderRequestException extends RecoverableException {
    public MismatchOrderRequestException() {
        super("입력값이 예시 형태와 다릅니다.");
    }
}
