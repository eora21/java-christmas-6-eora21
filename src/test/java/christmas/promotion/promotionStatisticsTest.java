package christmas.promotion;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import christmas.information.Amount;
import christmas.information.Quantity;
import christmas.menu.Menu;
import christmas.order.OrderDetail;
import christmas.order.Orders;
import christmas.promotion.information.Discount;
import christmas.promotion.information.Giveaway;
import christmas.promotion.information.PromotionBenefitInfo;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class promotionStatisticsTest {
    @Test
    @DisplayName("프로모션이 없는 통계 반환 시 획득하는 값들이 비어있다.")
    void emptyStatistics() {
        PromotionStatistics promotionStatistics = PromotionStatistics.emptyInstance(
                LocalDate.parse("2023-12-25"),
                new Orders(List.of(new OrderDetail(Menu.BARBECUE_RIBS, 20))));
        PromotionBenefitInfo promotionBenefitInfo = promotionStatistics.getPromotionBenefitInfo();
        assertThat(promotionBenefitInfo.getPromotionBenefitInfo()).isEmpty();
        assertThat(promotionBenefitInfo.getTotalBenefit().getBenefitAmount()).isEqualTo(Amount.createZeroAmount());
    }

    @Test
    @DisplayName("메뉴 할인이 있을 시 집계된다.")
    void promotionStatisticsMenuDiscount() {
        Promotion promotion = mock(Promotion.class);
        when(promotion.calculateMenuDiscount(any(), any()))
                .thenReturn(Map.of(Menu.ICE_CREAM, new Discount(1_000)));

        PromotionPlan promotionPlan = mock(PromotionPlan.class);
        when(promotionPlan.getPromotion()).thenReturn(promotion);

        PromotionStatistics promotionStatistics = new PromotionStatistics(List.of(promotionPlan),
                LocalDate.parse("2023-12-25"),
                new Orders(List.of(new OrderDetail(Menu.ICE_CREAM, 20))));

        assertThat(promotionStatistics.getPromotionBenefitInfo().getTotalBenefit().getBenefitAmount())
                .isEqualTo(new Amount(1_000));
        assertThat(promotionStatistics.getAfterDiscountAmount()).isEqualTo(new Amount(99_000));
    }

    @Test
    @DisplayName("증정품이 있을 시 집계된다.")
    void promotionStatisticsGiveaway() {
        Promotion promotion = mock(Promotion.class);
        Giveaway giveaway = new Giveaway(Menu.CHAMPAGNE, new Quantity(2));
        when(promotion.getGiveaway(any(), any()))
                .thenReturn(Optional.of(giveaway));

        PromotionPlan promotionPlan = mock(PromotionPlan.class);
        when(promotionPlan.getPromotion()).thenReturn(promotion);

        PromotionStatistics promotionStatistics = new PromotionStatistics(List.of(promotionPlan),
                LocalDate.parse("2023-12-25"),
                new Orders(List.of(new OrderDetail(Menu.BARBECUE_RIBS, 20))));

        assertThat(promotionStatistics.getPromotionBenefitInfo().getTotalBenefit().getBenefitAmount())
                .isEqualTo(Menu.CHAMPAGNE.getAmount().multiplyAmount(2));
        assertThat(promotionStatistics.getGiveaways())
                .containsExactly(giveaway);
    }

    @Test
    @DisplayName("전체 할인이 있을 시 집계된다.")
    void promotionStatisticsTotalDiscount() {
        Promotion promotion = mock(Promotion.class);
        when(promotion.calculateTotalDiscount(any(), any()))
                .thenReturn(Optional.of(new Discount(2_000)));

        PromotionPlan promotionPlan = mock(PromotionPlan.class);
        when(promotionPlan.getPromotion()).thenReturn(promotion);

        PromotionStatistics promotionStatistics = new PromotionStatistics(List.of(promotionPlan),
                LocalDate.parse("2023-12-25"),
                new Orders(List.of(new OrderDetail(Menu.ICE_CREAM, 20))));

        assertThat(promotionStatistics.getPromotionBenefitInfo().getTotalBenefit().getBenefitAmount())
                .isEqualTo(new Amount(2_000));
        assertThat(promotionStatistics.getAfterDiscountAmount()).isEqualTo(new Amount(98_000));
    }
}