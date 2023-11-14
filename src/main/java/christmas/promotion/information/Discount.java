package christmas.promotion.information;

import christmas.information.Amount;

public final class Discount implements Beneficial {
    private final Amount discountAmount;

    public Discount(int discountAmount) {
        this.discountAmount = new Amount(discountAmount);
    }

    public Discount(Amount discountAmount) {
        this.discountAmount = discountAmount;
    }

    public static Discount sum(Discount firstDiscount, Discount secondDiscount) {
        Amount firstDiscountAmount = firstDiscount.getDiscountAmount();
        Amount secondDiscountAmount = secondDiscount.getDiscountAmount();
        return new Discount(firstDiscountAmount.plusAmount(secondDiscountAmount));
    }

    public Amount calculateDiscount(Amount amount) {
        return amount.minusAmount(discountAmount);
    }

    public Amount getDiscountAmount() {
        return discountAmount;
    }

    @Override
    public Benefit convertToBenefit() {
        return new Benefit(getDiscountAmount());
    }
}
