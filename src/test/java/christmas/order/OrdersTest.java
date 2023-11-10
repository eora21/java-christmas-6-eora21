package christmas.order;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import christmas.exception.non_fatal.OverMaxTotalOrderQuantityException;
import christmas.exception.non_fatal.illegal_order.OrderMenuDuplicateException;
import christmas.menu.Menu;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrdersTest {
    @Test
    @DisplayName("주문의 총 수량이 20개를 넘으면 예외가 발생한다.")
    void createOrdersOverMaxOrderQuantity() {
        assertThrows(OverMaxTotalOrderQuantityException.class, () -> new Orders(List.of(
                new OrderDetail(Menu.CHOCOLATE_CAKE, 15),
                new OrderDetail(Menu.BARBECUE_RIBS, 6))
        ));
    }

    @Test
    @DisplayName("주문 메뉴가 중복되면 예외가 발생한다.")
    void createOrdersDuplicateOrderMenu() {
        assertThrows(OrderMenuDuplicateException.class, () -> new Orders(List.of(
                new OrderDetail(Menu.CHOCOLATE_CAKE, 15),
                new OrderDetail(Menu.CHOCOLATE_CAKE, 1))
        ));
    }

    @Test
    @DisplayName("주문 생성")
    void createOrders() {
        assertDoesNotThrow(() -> new Orders(List.of(
                new OrderDetail(Menu.CHOCOLATE_CAKE, 15),
                new OrderDetail(Menu.CAESAR_SALAD, 1))
        ));
    }
}