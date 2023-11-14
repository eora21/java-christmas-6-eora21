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
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class ChristmasDdayPromotionTest {
    Promotion christmasDdayPromotion = new ChristmasDdayPromotion();
    Orders orders = new Orders(List.of(new OrderDetail(Menu.ICE_CREAM, 1)));

    @ParameterizedTest
    @DisplayName("12월 1일 이전, 12월 25일 이후에 이벤트가 동작될 시 빈 옵셔널을 반환한다.")
    @CsvSource(value = {"2023-11-30", "2023-12-26"})
    void calculateDiscountAmountNotInEventDuration(LocalDate localDate) {
        assertThat(christmasDdayPromotion.calculateTotalDiscount(localDate, orders))
                .isEmpty();
    }

    @Test
    @DisplayName("크리스마스 디데이 이벤트는 증정 이벤트를 하지 않는다.")
    void getGiveawayEmpty() {
        assertThat(christmasDdayPromotion.getGiveaway(LocalDate.parse("2023-12-02"), orders))
                .isEmpty();
    }

    @ParameterizedTest
    @DisplayName("12월 1일의 할인 금액은 1,000원이며, 하루가 지날 때마다 할인 금액이 100원씩 올라간다.")
    @MethodSource("localDateAndExpectDiscountAmountValue")
    void calculateDiscountAmount(LocalDate localDate, int expectDiscountAmountValue) {
        Optional<Discount> discountOptional = christmasDdayPromotion.calculateTotalDiscount(localDate, orders);
        Discount discount = discountOptional.orElseThrow();
        assertThat(discount.getDiscountAmount()).isEqualTo(new Amount(expectDiscountAmountValue));
    }

    static Stream<Arguments> localDateAndExpectDiscountAmountValue() {
        return Stream.of(
                Arguments.of(LocalDate.parse("2023-12-01"), 1_000),
                Arguments.of(LocalDate.parse("2023-12-02"), 1_100),
                Arguments.of(LocalDate.parse("2023-12-25"), 3_400)
        );
    }
}