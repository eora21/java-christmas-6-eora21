package christmas.promotion.discount_promotion;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.information.Amount;
import christmas.menu.Menu;
import christmas.menu.MenuGroup;
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

@DisplayName("토요일, 일요일에 에피타이저 주문 시 개당 2_023원 할인")
class DayOfTheWeekPromotionTest {
    Promotion dayOfTheWeekPromotion = new DayOfTheWeekPromotion(List.of(SATURDAY, SUNDAY), MenuGroup.APPETIZER);
    Orders orders = new Orders(List.of(
            new OrderDetail(Menu.BARBECUE_RIBS, 1),
            new OrderDetail(Menu.TAPAS, 2),
            new OrderDetail(Menu.MUSHROOM_SOUP, 3)));

    @ParameterizedTest(name = "{1}에 주문할 시 할인되지 않는다.")
    @CsvSource({"2023-12-11, 월요일", "2023-12-12, 화요일", "2023-12-13, 수요일", "2023-12-14, 목요일", "2023-12-15, 금요일"})
    void orderOutOfPromotionDayOfWeek(LocalDate localDate, String dayOfTheWeek) {
        Optional<Discount> discountOptional = dayOfTheWeekPromotion.calculateDiscount(localDate, orders);
        assertThat(discountOptional).isEmpty();
    }

    @ParameterizedTest(name = "{1}에 주문할 시 할인된다.")
    @CsvSource({"2023-12-16, 토요일", "2023-12-17, 일요일"})
    void orderInPromotionDayOfWeek(LocalDate localDate, String dayOfTheWeek) {
        Optional<Discount> discountOptional = dayOfTheWeekPromotion.calculateDiscount(localDate, orders);
        assertThat(discountOptional).isNotEmpty();
    }

    @Test
    @DisplayName("에피타이저 주문량이 없을 시 할인되지 않는다.")
    void calculateDiscountAmountNotIncludeDiscountMenuGroup() {
        Orders ordersNotIncludeAppetizer = new Orders(List.of(
                new OrderDetail(Menu.ICE_CREAM, 1),
                new OrderDetail(Menu.CHAMPAGNE, 2),
                new OrderDetail(Menu.BARBECUE_RIBS, 3)));

        Optional<Discount> discountOptional =
                dayOfTheWeekPromotion.calculateDiscount(LocalDate.parse("2023-12-16"), ordersNotIncludeAppetizer);

        assertThat(discountOptional).isEmpty();
    }

    @Test
    @DisplayName("할인 금액은 일치하는 에피타이저 총 주문량(2 + 3) * 할인 가격(2,023)이다.")
    void calculateDiscountAmount() {
        Discount discount = dayOfTheWeekPromotion.calculateDiscount(LocalDate.parse("2023-12-16"), orders)
                .orElseThrow();
        assertThat(discount.getDiscountAmount()).isEqualTo(new Amount((2 + 3) * 2_023));
    }

    @Test
    @DisplayName("요일 이벤트는 증정 이벤트를 하지 않는다.")
    void getGiveawayEmpty() {
        assertThat(dayOfTheWeekPromotion.getGiveaway(LocalDate.parse("2023-12-16"), orders))
                .isEmpty();
    }
}