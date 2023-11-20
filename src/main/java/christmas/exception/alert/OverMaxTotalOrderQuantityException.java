package christmas.exception.alert;

public class OverMaxTotalOrderQuantityException extends AlertException {
    public OverMaxTotalOrderQuantityException(int totalOrderSize) {
        super("메뉴는 한 번에 최대 " + totalOrderSize + "개까지만 주문할 수 있습니다.");
    }
}
