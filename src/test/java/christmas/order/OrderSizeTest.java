package christmas.order;

import static org.junit.jupiter.api.Assertions.*;

import christmas.exception.custom.OrderSizeNotPositiveException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderSizeTest {
    @ParameterizedTest
    @DisplayName("주문 개수가 양수가 아닐 때 예외가 발생한다.")
    @ValueSource(ints = {-1, 0})
    void createOrderSizeByNotPositiveSize(int orderSize) {
        assertThrows(OrderSizeNotPositiveException.class, () -> new OrderSize(orderSize));
    }

    @Test
    @DisplayName("주문 개수 생성")
    void createOrderSize() {
        assertDoesNotThrow(() -> new OrderSize(1));
    }
}