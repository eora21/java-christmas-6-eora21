package christmas.order;

import christmas.exception.custom.OrderMenuDuplicateException;
import christmas.exception.custom.OverMaxTotalOrderSizeException;
import christmas.menu.Menu;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Orders {
    private static final int MAX_TOTAL_ORDER_SIZE = 20;
    private final List<OrderDetail> orderDetails;

    public Orders(List<OrderDetail> orderDetails) {
        validate(orderDetails);
        this.orderDetails = orderDetails;
    }

    private void validate(List<OrderDetail> orderDetails) {
        validateTotalOrderSize(orderDetails);
        validateOrderMenuDuplicate(orderDetails);
    }

    private void validateTotalOrderSize(List<OrderDetail> orderDetails) {
        int totalOrderSize = orderDetails.stream()
                .map(OrderDetail::getOrderQuantity)
                .mapToInt(OrderQuantity::orderQuantity)
                .sum();

        if (MAX_TOTAL_ORDER_SIZE < totalOrderSize) {
            throw new OverMaxTotalOrderSizeException(MAX_TOTAL_ORDER_SIZE);
        }
    }

    private void validateOrderMenuDuplicate(List<OrderDetail> orderDetails) {
        Set<Menu> orderMenuGroup = orderDetails.stream()
                .map(OrderDetail::getMenu)
                .collect(Collectors.toUnmodifiableSet());

        if (orderDetails.size() != orderMenuGroup.size()) {
            throw new OrderMenuDuplicateException();
        }
    }
}
