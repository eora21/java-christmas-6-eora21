package christmas.exception.recoverable;

public class QuantityNotPositiveException extends RecoverableException {
    public QuantityNotPositiveException() {
        super("수량은 양수여야 합니다.");
    }
}
