package christmas.promotion.giveaway_promotion;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import christmas.information.Amount;
import christmas.information.Quantity;
import christmas.menu.Menu;
import christmas.order.Orders;
import christmas.promotion.Promotion;
import christmas.promotion.information.Giveaway;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ChristmasGiveawayPromotionTest {
    Promotion christmasGiveawayPromotion = new ChristmasGiveawayPromotion();

    @Test
    @DisplayName("주문 총액이 150,000 이하라면 증정품이 제공되지 않는다.")
    void giveawayNotOfferWhenTotalOrderAmountLessThanGiveawayAmount() {
        Orders mockOrders = mock(Orders.class);
        when(mockOrders.getOrdersTotalAmount())
                .thenReturn(new Amount(119_999));

        Optional<Giveaway> giveawayOptional =
                christmasGiveawayPromotion.getGiveaway(LocalDate.parse("2023-12-25"), mockOrders);
        assertThat(giveawayOptional).isEmpty();
    }

    @ParameterizedTest
    @DisplayName("주문 총액이 150,000 이상이라면 증정품이 제공된다.")
    @ValueSource(ints = {120_000, 130_000})
    void giveawayOffer(int totalAmount) {
        Orders mockOrders = mock(Orders.class);
        when(mockOrders.getOrdersTotalAmount())
                .thenReturn(new Amount(totalAmount));

        Giveaway giveaway = christmasGiveawayPromotion.getGiveaway(LocalDate.parse("2023-12-25"), mockOrders)
                .orElseThrow();
        assertThat(giveaway.getMenu()).isEqualByComparingTo(Menu.CHAMPAGNE);
        assertThat(giveaway.getQuantity()).isEqualTo(new Quantity(1));
    }

    @Test
    @DisplayName("증정 이벤트는 할인 이벤트를 하지 않는다.")
    void getGiveawayEmpty() {
        Orders mockOrders = mock(Orders.class);
        assertThat(christmasGiveawayPromotion.calculateTotalDiscount(LocalDate.parse("2023-12-25"), mockOrders))
                .isEmpty();
    }
}