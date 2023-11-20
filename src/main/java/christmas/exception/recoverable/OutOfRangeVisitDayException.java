package christmas.exception.recoverable;

public class OutOfRangeVisitDayException extends RecoverableException {
    public OutOfRangeVisitDayException() {
        super("방문일의 범위를 벗어났습니다.");
    }
}
