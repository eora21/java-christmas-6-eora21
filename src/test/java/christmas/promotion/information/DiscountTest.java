package christmas.promotion.information;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.information.Amount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DiscountTest {
    @Test
    @DisplayName("할인 합계 시 할인값이 합쳐진다.")
    void sumDiscount() {
        Discount sum = Discount.sum(new Discount(new Amount(100)), new Discount(new Amount(200)));
        assertThat(sum.getDiscountAmount()).isEqualTo(new Amount(300));
    }

    @Test
    @DisplayName("할인 시 금액이 할인값만큼 내려간 새 금액을 반환한다.")
    void discountAmount() {
        Discount discount = new Discount(new Amount(100));
        Amount amount = discount.calculateDiscount(new Amount(500));
        assertThat(amount).isEqualTo(new Amount(400));
    }
}