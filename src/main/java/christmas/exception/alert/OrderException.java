package christmas.exception.alert;

public class OrderException extends AlertException {
    public OrderException() {
        super("유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}
