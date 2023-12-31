package christmas.information;

import christmas.exception.recoverable.QuantityNotPositiveException;

public record Quantity(int quantity) {
    private static final int MIN_QUANTITY = 1;

    public Quantity {
        validateQuantity(quantity);
    }

    private void validateQuantity(int quantity) {
        if (quantity < MIN_QUANTITY) {
            throw new QuantityNotPositiveException();
        }
    }
}
