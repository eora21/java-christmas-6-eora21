package christmas.exception.custom;

public class OverMaxTotalOrderSizeException extends CustomException {
    public OverMaxTotalOrderSizeException(int totalOrderSize) {
        super(String.format("최대 주문할 수 있는 개수는 %,d입니다.", totalOrderSize));
    }
}
