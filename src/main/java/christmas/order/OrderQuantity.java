package christmas.order;

import christmas.exception.custom.OrderQuantityNotPositiveException;

public record OrderQuantity(int orderQuantity) {
    private static final int MIN_ORDER_QUANTITY = 1;

    public OrderQuantity {
        validateOrderQuantity(orderQuantity);
    }

    private void validateOrderQuantity(int orderQuantity) {
        if (orderQuantity < MIN_ORDER_QUANTITY) {
            throw new OrderQuantityNotPositiveException();
        }
    }
}
