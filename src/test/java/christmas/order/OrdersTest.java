package christmas.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import christmas.exception.alert.OrderOnlyOneMenuGroupException;
import christmas.exception.alert.OverMaxTotalOrderQuantityException;
import christmas.exception.recoverable.MenuNotExistException;
import christmas.exception.recoverable.OrderEmptyException;
import christmas.exception.recoverable.OrderMenuDuplicateException;
import christmas.exception.recoverable.QuantityNotPositiveException;
import christmas.information.Quantity;
import christmas.menu.Menu;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrdersTest {
    @Test
    @DisplayName("아무것도 주문하지 않았다면 예외가 발생한다.")
    void createOrdersEmptyOrder() {
        assertThrows(OrderEmptyException.class, () -> new Orders(Collections.emptyList()));
    }

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
    @DisplayName("음료만 주문하면 예외가 발생한다.")
    void createOrdersOnlyDrink() {
        assertThrows(OrderOnlyOneMenuGroupException.class, () -> new Orders(List.of(
                new OrderDetail(Menu.ZERO_COLA, 15),
                new OrderDetail(Menu.RED_WINE, 2),
                new OrderDetail(Menu.CHAMPAGNE, 1))
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

    @Test
    @DisplayName("주문 시 존재하지 않는 메뉴를 요청하면 예외가 발생한다.")
    void orderNotExistMenu() {
        assertThrows(MenuNotExistException.class, () ->
                Orders.newInstance(List.of(new OrderRequest("자바초코칩프라푸치노", 1))));
    }

    @Test
    @DisplayName("주문 시 중복된 메뉴를 주문하면 예외가 발생한다.")
    void orderDuplicateMenu() {
        assertThrows(OrderMenuDuplicateException.class, () ->
                Orders.newInstance(List.of(
                        new OrderRequest("아이스크림", 1),
                        new OrderRequest("아이스크림", 16)
                )));
    }

    @ParameterizedTest
    @DisplayName("주문 시 수량이 0 이하면 예외가 발생한다.")
    @ValueSource(ints = {-1, 0})
    void orderNotPositiveQuantity(int orderQuantity) {
        assertThrows(QuantityNotPositiveException.class, () ->
                Orders.newInstance(List.of(new OrderRequest("아이스크림", orderQuantity))));
    }

    @Test
    @DisplayName("주문 총 수량이 20을 넘으면 예외가 발생한다.")
    void orderOverMaxTotalOrderQuantity() {
        assertThrows(OverMaxTotalOrderQuantityException.class, () ->
                Orders.newInstance(List.of(
                        new OrderRequest("아이스크림", 15),
                        new OrderRequest("초코케이크", 6)
                )));
    }

    @Test
    @DisplayName("음료만 주문했을 시 예외가 발생한다.")
    void orderOnlyDrink() {
        assertThrows(OrderOnlyOneMenuGroupException.class, () ->
                Orders.newInstance(List.of(
                        new OrderRequest("제로콜라", 1),
                        new OrderRequest("레드와인", 2),
                        new OrderRequest("샴페인", 3)
                )));
    }

    @Test
    @DisplayName("주문 목록 생성")
    void order() {
        Orders orders = Orders.newInstance(List.of(
                new OrderRequest("아이스크림", 15),
                new OrderRequest("초코케이크", 5)));

        List<OrderDetail> orderDetails = orders.getOrderDetails();

        int iceCreamOrderQuantity = getOrderDetailQuantity(orderDetails, Menu.ICE_CREAM);
        int chocolateCakeOrderQuantity = getOrderDetailQuantity(orderDetails, Menu.CHOCOLATE_CAKE);

        assertThat(iceCreamOrderQuantity).isEqualTo(15);
        assertThat(chocolateCakeOrderQuantity).isEqualTo(5);
    }

    int getOrderDetailQuantity(List<OrderDetail> orderDetails, Menu menu) {
        return orderDetails.stream()
                .filter(orderDetail -> orderDetail.getMenu() == menu)
                .map(OrderDetail::getQuantity)
                .mapToInt(Quantity::quantity)
                .findAny()
                .orElseThrow();
    }
}