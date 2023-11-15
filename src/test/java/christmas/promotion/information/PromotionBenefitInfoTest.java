package christmas.promotion.information;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.information.Amount;
import christmas.promotion.PromotionPlan;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PromotionBenefitInfoTest {
    @Test
    @DisplayName("모든 프로모션 혜택의 총 합을 계산한다.")
    void calculateTotalBenefit() {
        PromotionBenefitInfo promotionBenefitInfo = new PromotionBenefitInfo(Map.of(
                PromotionPlan.WEEKDAY_PROMOTION, new Benefit(1_000),
                PromotionPlan.WEEKEND_PROMOTION, new Benefit(2_000),
                PromotionPlan.CHRISTMAS_GIVEAWAY_PROMOTION, new Benefit(3_000)
        ));

        Benefit totalBenefit = promotionBenefitInfo.getTotalBenefit();
        assertThat(totalBenefit.getBenefitAmount()).isEqualTo(new Amount(6_000));
    }
}