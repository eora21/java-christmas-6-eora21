package christmas.promotion;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.promotion.information.Benefit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PromotionBadgeTest {
    @ParameterizedTest
    @DisplayName("혜택에 따른 배지를 수령한다.")
    @CsvSource({"5_000, STAR", "9_999, STAR", "10_000, TREE", "19_999, TREE", "20_000, SANTA"})
    void getBadge(int benefitAmount, PromotionBadge badge) {
        assertThat(PromotionBadge.getBadge(new Benefit(benefitAmount)))
                .hasValue(badge);
    }

    @Test
    @DisplayName("혜택이 모자르면 배지를 받지 못 한다.")
    void getNonBadge() {
        assertThat(PromotionBadge.getBadge(new Benefit(4_999)))
                .isEmpty();
    }
}