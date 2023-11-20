package christmas.exception.recoverable;

public class ParseIntException extends RecoverableException {
    public ParseIntException() {
        super("정수로 변환할 수 없습니다.");
    }
}
