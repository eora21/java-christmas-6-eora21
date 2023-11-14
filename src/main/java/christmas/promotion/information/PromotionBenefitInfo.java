package christmas.promotion.information;

import christmas.information.Amount;
import christmas.promotion.Promotion;
import java.util.Map;
import java.util.Optional;

public class PromotionBenefitInfo {
    private final Map<Promotion, Benefit> promotionBenefitInfo;

    public PromotionBenefitInfo(Map<Promotion, Benefit> promotionBenefitInfo) {
        this.promotionBenefitInfo = promotionBenefitInfo;
    }

    public Map<Promotion, Benefit> getPromotionBenefitInfo() {
        return promotionBenefitInfo;
    }

    public Benefit getTotalBenefit() {
        return promotionBenefitInfo.values()
                .stream()
                .reduce(Benefit::sum)
                .orElseGet(() -> new Benefit(Amount.createZeroAmount()));
    }
}
