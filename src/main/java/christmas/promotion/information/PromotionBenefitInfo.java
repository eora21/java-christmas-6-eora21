package christmas.promotion.information;

import christmas.information.Amount;
import christmas.promotion.Promotion;
import christmas.promotion.PromotionPlan;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

public class PromotionBenefitInfo {
    private final Map<PromotionPlan, Benefit> promotionBenefitInfo;

    public PromotionBenefitInfo(Map<PromotionPlan, Benefit> promotionBenefitInfo) {
        this.promotionBenefitInfo = new EnumMap<>(promotionBenefitInfo);
    }

    public Map<PromotionPlan, Benefit> getPromotionBenefitInfo() {
        return Collections.unmodifiableMap(promotionBenefitInfo);
    }

    public Benefit getTotalBenefit() {
        return promotionBenefitInfo.values()
                .stream()
                .reduce(Benefit::sum)
                .orElseGet(() -> new Benefit(Amount.createZeroAmount()));
    }
}
