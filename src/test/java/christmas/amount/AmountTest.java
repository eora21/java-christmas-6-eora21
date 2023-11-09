package christmas.amount;

import static org.junit.jupiter.api.Assertions.*;

import christmas.exception.NegativeAmountException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class AmountTest {
    @Test
    @DisplayName("음수인 금액을 만들면 예외를 발생시킨다.")
    void createAmountByNegativeValue() {
        assertThrows(NegativeAmountException.class, () -> new Amount(-1));
    }

    @ParameterizedTest
    @DisplayName("정상 금액 생성")
    @ValueSource(ints = {0, 1})
    void createAmount(int amount) {
        assertDoesNotThrow(() -> new Amount(amount));
    }
}