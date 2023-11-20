package christmas.information;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import christmas.exception.fatal.NegativeAmountException;
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

    @Test
    @DisplayName("금액을 더한다.")
    void plusAmount() {
        assertThat(new Amount(100).plusAmount(new Amount(200))).isEqualTo(new Amount(300));
    }

    @Test
    @DisplayName("금액을 곱한다.")
    void multiplyAmount() {
        assertThat(new Amount(200).multiplyAmount(11)).isEqualTo(new Amount(2_200));
    }

    @Test
    @DisplayName("금액을 차감한다.")
    void minusAmount() {
        assertThat(new Amount(200).minusAmount(new Amount(50))).isEqualTo(new Amount(150));
    }

    @Test
    @DisplayName("원래 금액보다 많은 금액을 차감 시 0원이 된다.")
    void minusOverAmount() {
        assertThat(new Amount(200).minusAmount(new Amount(500))).isEqualTo(Amount.createZeroAmount());
    }
}