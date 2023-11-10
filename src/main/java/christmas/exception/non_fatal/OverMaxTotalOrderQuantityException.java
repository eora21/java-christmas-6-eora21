package christmas.exception.non_fatal;

public class OverMaxTotalOrderQuantityException extends CustomException {
    public OverMaxTotalOrderQuantityException(int totalOrderSize) {
        super("메뉴는 한 번에 최대 " + totalOrderSize + "개까지만 주문할 수 있습니다.");
    }
}
