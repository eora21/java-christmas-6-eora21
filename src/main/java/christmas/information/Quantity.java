package christmas.information;

import christmas.exception.non_fatal.illegal_order.OrderQuantityNotPositiveException;

public record Quantity(int quantity) {
    private static final int MIN_QUANTITY = 1;

    public Quantity {
        validateQuantity(quantity);
    }

    private void validateQuantity(int quantity) {
        if (quantity < MIN_QUANTITY) {
            throw new OrderQuantityNotPositiveException();
        }
    }
}
