package christmas.information;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import christmas.exception.non_fatal.illegal_order.OrderQuantityNotPositiveException;
import christmas.information.Quantity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class QuantityTest {
    @ParameterizedTest
    @DisplayName("주문 수량이 양수가 아닐 때 예외가 발생한다.")
    @ValueSource(ints = {-1, 0})
    void createOrderQuantityNotPositive(int orderQuantity) {
        assertThrows(OrderQuantityNotPositiveException.class, () -> new Quantity(orderQuantity));
    }

    @Test
    @DisplayName("주문 수량 생성")
    void createOrderQuantity() {
        assertDoesNotThrow(() -> new Quantity(1));
    }
}