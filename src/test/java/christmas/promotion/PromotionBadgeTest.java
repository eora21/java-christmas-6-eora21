package christmas.promotion;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.promotion.information.Benefit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PromotionBadgeTest {
    @ParameterizedTest
    @DisplayName("혜택에 따른 배지를 수령한다.")
    @CsvSource({"0, NONE", "4_999, NONE", "5_000, STAR", "9_999, STAR", "10_000, TREE", "19_999, TREE", "20_000, SANTA"})
    void getBadge(int benefitAmount, PromotionBadge badge) {
        assertThat(PromotionBadge.getBadge(new Benefit(benefitAmount)))
                .isEqualTo(badge);
    }
}