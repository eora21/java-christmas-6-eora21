package christmas.menu;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import christmas.exception.non_fatal.illegal_order.OrderMenuNotExistException;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MenuTest {
    @Test
    @DisplayName("존재하지 않은 메뉴를 요청할 시 예외가 발생한다.")
    void findNotExistMenu() {
        assertThrows(OrderMenuNotExistException.class, () -> Menu.findMenu("자바초코칩프라푸치노"));
    }

    @ParameterizedTest
    @DisplayName("메뉴 요청")
    @MethodSource("orderMenuNameAndExpectMenu")
    void findMenu(String orderMenuName, Menu expectMenu) {
        assertThat(Menu.findMenu(orderMenuName)).isEqualByComparingTo(expectMenu);
    }

    public static Stream<Arguments> orderMenuNameAndExpectMenu() {
        return Stream.of(
                Arguments.of("양송이수프", Menu.MUSHROOM_SOUP),
                Arguments.of("타파스", Menu.TAPAS),
                Arguments.of("시저샐러드", Menu.CAESAR_SALAD),
                Arguments.of("티본스테이크", Menu.T_BONE_STEAK),
                Arguments.of("바비큐립", Menu.BARBECUE_RIBS),
                Arguments.of("해산물파스타", Menu.SEAFOOD_PASTA),
                Arguments.of("크리스마스파스타", Menu.CHRISTMAS_PASTA),
                Arguments.of("초코케이크", Menu.CHOCOLATE_CAKE),
                Arguments.of("아이스크림", Menu.ICE_CREAM),
                Arguments.of("제로콜라", Menu.ZERO_COLA),
                Arguments.of("레드와인", Menu.RED_WINE),
                Arguments.of("샴페인", Menu.CHAMPAGNE)
        );
    }
}