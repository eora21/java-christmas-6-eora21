package christmas.menu;

import static christmas.menu.MenuGroup.APPETIZER;
import static christmas.menu.MenuGroup.DESSERT;
import static christmas.menu.MenuGroup.DRINK;
import static christmas.menu.MenuGroup.MAIN;

import christmas.amount.Amount;
import christmas.exception.non_fatal.illegal_order.OrderMenuNotExistException;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Menu {
    MUSHROOM_SOUP("양송이수프", 6_000, APPETIZER),
    TAPAS("타파스", 5_500, APPETIZER),
    CAESAR_SALAD("시저샐러드", 8_000, APPETIZER),
    T_BONE_STEAK("티본스테이크", 55_000, MAIN),
    BARBECUE_RIBS("바비큐립", 54_000, MAIN),
    SEAFOOD_PASTA("해산물파스타", 35_000, MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, MAIN),
    CHOCOLATE_CAKE("초코케이크", 15_000, DESSERT),
    ICE_CREAM("아이스크림", 5_000, DESSERT),
    ZERO_COLA("제로콜라", 3_000, DRINK),
    RED_WINE("레드와인", 60_000, DRINK),
    CHAMPAGNE("샴페인", 25_000, DRINK);

    private final String name;
    private final Amount amount;
    private final MenuGroup menuGroup;
    Menu(String name, int amount, MenuGroup menuGroup) {
        this.name = name;
        this.amount = new Amount(amount);
        this.menuGroup = menuGroup;
    }

    private static final Map<String, Menu> MENU_BOARD = createMenuBoard();

    private static Map<String, Menu> createMenuBoard() {
        return Arrays.stream(Menu.values())
                .collect(Collectors.toUnmodifiableMap(Menu::getName, Function.identity()));
    }

    public static Menu findMenu(String orderMenuName) {
        if (MENU_BOARD.containsKey(orderMenuName)) {
            return MENU_BOARD.get(orderMenuName);
        }

        throw new OrderMenuNotExistException();
    }

    public String getName() {
        return name;
    }

    public Amount getAmount() {
        return amount;
    }

    public MenuGroup getMenuGroup() {
        return menuGroup;
    }
}
