package christmas.exception.recoverable;

public class OrderEmptyException extends RecoverableException {
    public OrderEmptyException() {
        super("주문 메뉴 목록이 비어있습니다.");
    }
}
