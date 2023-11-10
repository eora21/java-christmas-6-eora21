package christmas.exception.custom;

public class OrderSizeNotPositiveException extends CustomException {
    public OrderSizeNotPositiveException() {
        super("주문 개수는 양수여야 합니다.");
    }
}
