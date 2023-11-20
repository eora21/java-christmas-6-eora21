package christmas.exception.fatal;

public class NegativeAmountException extends FatalException {
    public NegativeAmountException() {
        super("금액은 음수일 수 없습니다.");
    }
}
