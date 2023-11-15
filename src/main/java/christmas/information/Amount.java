package christmas.information;

import christmas.exception.fatal.NegativeAmountException;

public record Amount(int amount) implements Comparable<Amount> {
    private static final int ZERO_AMOUNT = 0;
    public Amount {
        validateAmount(amount);
    }

    public static Amount createZeroAmount() {
        return new Amount(ZERO_AMOUNT);
    }

    private void validateAmount(int amount) {
        if (amount < ZERO_AMOUNT) {
            throw new NegativeAmountException();
        }
    }

    public Amount plusAmount(Amount otherAmount) {
        return new Amount(this.amount + otherAmount.amount);
    }

    public Amount multiplyAmount(int multiplyValue) {
        return new Amount(this.amount * multiplyValue);
    }

    public Amount minusAmount(Amount otherAmount) {
        return new Amount(Math.max(ZERO_AMOUNT, amount - otherAmount.amount));
    }

    public boolean isZeroAmount() {
        return amount == ZERO_AMOUNT;
    }

    @Override
    public int compareTo(Amount otherAmount) {
        return Integer.compare(this.amount, otherAmount.amount);
    }
}
