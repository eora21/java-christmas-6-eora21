package christmas.promotion.information;

import christmas.information.Amount;

public class Benefit {
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
}
