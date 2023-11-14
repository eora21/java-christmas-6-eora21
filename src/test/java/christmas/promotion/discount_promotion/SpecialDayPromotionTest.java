package christmas.promotion.discount_promotion;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.information.Amount;
import christmas.menu.Menu;
import christmas.order.OrderDetail;
import christmas.order.Orders;
import christmas.promotion.Promotion;
import christmas.promotion.information.Discount;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SpecialDayPromotionTest {
    Promotion specialDayPromotion = new SpecialDayPromotion();
    Orders orders = new Orders(List.of(new OrderDetail(Menu.MUSHROOM_SOUP, 2)));

    @Test
    @DisplayName("지정된 날짜가 아니라면 할인받지 못 한다.")
    void discountEmptyWhenNormalDay() {
        Optional<Discount> discountOptional =
                specialDayPromotion.calculateTotalDiscount(LocalDate.parse("2023-12-26"), orders);
        assertThat(discountOptional).isEmpty();
    }

    @ParameterizedTest
    @DisplayName("지정된 날짜일 경우 할인받는다.")
    @CsvSource({"2023-12-03", "2023-12-10", "2023-12-17", "2023-12-24", "2023-12-25", "2023-12-31"})
    void discountWhenSpecialDay(LocalDate localDate) {
        Discount discount = specialDayPromotion.calculateTotalDiscount(localDate, orders)
                .orElseThrow();
        assertThat(discount.getDiscountAmount()).isEqualTo(new Amount(1_000));
    }

    @Test
    @DisplayName("특별 이벤트는 증정 이벤트를 하지 않는다.")
    void getGiveawayEmpty() {
        assertThat(specialDayPromotion.getGiveaway(LocalDate.parse("2023-12-25"), orders))
                .isEmpty();
    }
}