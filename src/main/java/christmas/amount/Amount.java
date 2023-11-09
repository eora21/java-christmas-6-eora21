package christmas.amount;

import christmas.exception.NegativeAmountException;

public record Amount(int amount) {
    private static final int ZERO_AMOUNT = 0;
    public Amount {
        validateAmount(amount);
    }

    private void validateAmount(int amount) {
        if (amount < ZERO_AMOUNT) {
            throw new NegativeAmountException();
        }
    }
}
