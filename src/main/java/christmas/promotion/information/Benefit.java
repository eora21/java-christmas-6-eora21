package christmas.promotion.information;

import christmas.information.Amount;

public class Benefit implements Comparable<Benefit> {
    private final Amount benefitAmount;

    public Benefit(int benefitAmount) {
        this.benefitAmount = new Amount(benefitAmount);
    }

    public Benefit(Amount benefitAmount) {
        this.benefitAmount = benefitAmount;
    }

    public static Benefit sum(Benefit firstBenefit, Benefit secondBenefit) {
        Amount firstBenefitAmount = firstBenefit.getBenefitAmount();
        Amount secondBenefitAmount = secondBenefit.getBenefitAmount();
        return new Benefit(firstBenefitAmount.plusAmount(secondBenefitAmount));
    }

    public Amount getBenefitAmount() {
        return benefitAmount;
    }

    @Override
    public int compareTo(Benefit o) {
        return benefitAmount.compareTo(o.benefitAmount);
    }

    public boolean isGreaterOrEqual(Benefit otherBenefit) {
        return benefitAmount.isGreaterOrEqual(otherBenefit.benefitAmount);
    }
}
