package christmas.promotion.information;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.information.Amount;
import christmas.menu.Menu;
import christmas.order.OrderDetail;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuDiscountTest {
    @Test
    @DisplayName("메뉴에 대한 할인이 주어졌을 시, 주문 정보를 통해 할인값을 도출한다.")
    void calculateMenuDiscount() {
        Amount champagneAmount = Menu.CHAMPAGNE.getAmount();

        MenuDiscount menuDiscount = new MenuDiscount(Map.of(Menu.CHAMPAGNE, new Discount(new Amount(2_000))));
        assertThat(menuDiscount.calculateMenuDiscount(new OrderDetail(Menu.CHAMPAGNE, 2)))
                .isEqualTo(new Amount(champagneAmount.amount() * 2 - 2_000));
    }

    @Test
    @DisplayName("메뉴에 대한 할인이 일치하지 않는다면 할인되지 않는다.")
    void calculateMissMenuDiscount() {
        Amount redWineAmount = Menu.RED_WINE.getAmount();

        MenuDiscount menuDiscount = new MenuDiscount(Map.of(Menu.CHAMPAGNE, new Discount(new Amount(2_000))));
        assertThat(menuDiscount.calculateMenuDiscount(new OrderDetail(Menu.RED_WINE, 2)))
                .isEqualTo(new Amount(redWineAmount.amount() * 2));
    }
}