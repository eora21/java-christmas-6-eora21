package christmas.exception.critical;

public class NegativeAmountException extends IllegalStateException {
    public NegativeAmountException() {
        super("금액은 음수일 수 없습니다.");
    }
}
