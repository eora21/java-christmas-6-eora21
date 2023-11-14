package christmas.order;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.information.Amount;
import christmas.menu.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderDetailTest {
    @Test
    @DisplayName("주문에 대한 가격을 반환한다.")
    void calculateOrderDetailAmount() {
        Amount tapasAmount = Menu.TAPAS.getAmount();

        OrderDetail orderDetail = new OrderDetail(Menu.TAPAS, 9);
        assertThat(orderDetail.calculateOrderDetailAmount())
                .isEqualTo(new Amount(tapasAmount.amount() * 9));
    }
}