package christmas.order;

import christmas.exception.custom.OrderSizeNotPositiveException;

public record OrderSize(int orderSize) {
    private static final int MIN_ORDER_SIZE = 1;

    public OrderSize {
        validateOrderSize(orderSize);
    }

    private void validateOrderSize(int orderSize) {
        if (orderSize < MIN_ORDER_SIZE) {
            throw new OrderSizeNotPositiveException();
        }
    }
}
