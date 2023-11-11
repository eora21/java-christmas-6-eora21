package christmas.information;

import christmas.exception.fatal.NegativeAmountException;

public record Amount(int amount) implements Comparable<Amount> {
    private static final int ZERO_AMOUNT = 0;
    public Amount {
        validateAmount(amount);
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

    @Override
    public int compareTo(Amount otherAmount) {
        return Integer.compare(this.amount, otherAmount.amount);
    }
}
