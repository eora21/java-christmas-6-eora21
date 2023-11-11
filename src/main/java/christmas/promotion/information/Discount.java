package christmas.promotion.information;

import christmas.information.Amount;

public final class Discount extends Benefit {
    private final Amount amount;

    public Discount(Amount amount) {
        this.amount = amount;
    }

    @Override
    public Amount getBenefitAmount() {
        return amount;
    }

    public Amount getDiscountAmount() {
        return amount;
    }
}
