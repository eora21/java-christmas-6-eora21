package christmas.exception.recoverable;

public class OrderMenuDuplicateException extends RecoverableException {
    public OrderMenuDuplicateException() {
        super("주문 메뉴가 중복되었습니다.");
    }
}
