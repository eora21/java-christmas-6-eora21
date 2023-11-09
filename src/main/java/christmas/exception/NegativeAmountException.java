package christmas.exception;

public class NegativeAmountException extends CustomException {
    public NegativeAmountException() {
        super("금액은 음수일 수 없습니다.");
    }
}
