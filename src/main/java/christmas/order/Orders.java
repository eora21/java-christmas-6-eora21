package christmas.order;

import christmas.exception.custom.OverMaxTotalOrderSizeException;
import java.util.List;

public class Orders {
    private static final int MAX_TOTAL_ORDER_SIZE = 20;
    private final List<OrderDetail> orderDetails;

    public Orders(List<OrderDetail> orderDetails) {
        validateTotalOrderSize(orderDetails);
        this.orderDetails = orderDetails;
    }

    private void validateTotalOrderSize(List<OrderDetail> orderDetails) {
        int totalOrderSize = orderDetails.stream()
                .map(OrderDetail::getOrderSize)
                .mapToInt(OrderSize::orderSize)
                .sum();

        if (MAX_TOTAL_ORDER_SIZE < totalOrderSize) {
            throw new OverMaxTotalOrderSizeException(MAX_TOTAL_ORDER_SIZE);
        }
    }
}
