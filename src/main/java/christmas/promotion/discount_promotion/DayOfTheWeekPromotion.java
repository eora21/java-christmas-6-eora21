package christmas.promotion.discount_promotion;

import christmas.information.Amount;
import christmas.information.Quantity;
import christmas.menu.Menu;
import christmas.menu.MenuGroup;
import christmas.order.OrderDetail;
import christmas.order.Orders;
import christmas.promotion.Promotion;
import christmas.promotion.information.Discount;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DayOfTheWeekPromotion implements Promotion {
    private static final Amount UNIT_DISCOUNT_AMOUNT = new Amount(2_023);
    private final List<DayOfWeek> dayOfWeeks;
    private final MenuGroup menuGroup;

    public DayOfTheWeekPromotion(List<DayOfWeek> dayOfWeeks, MenuGroup menuGroup) {
        this.dayOfWeeks = dayOfWeeks;
        this.menuGroup = menuGroup;
    }

    @Override
    public Map<Menu, Discount> calculateMenuDiscount(LocalDate localDate, Orders orders) {
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();

        if (dayOfWeeks.contains(dayOfWeek)) {
            return calculateDiscountInMenuGroup(orders);
        }

        return Collections.emptyMap();
    }

    private Map<Menu, Discount> calculateDiscountInMenuGroup(Orders orders) {
        return orders.filterOrderDetailUsingMenuGroup(menuGroup).stream()
                .collect(Collectors.toUnmodifiableMap(
                        OrderDetail::getMenu,
                        orderDetail -> discountMultiplyQuantity(orderDetail.getQuantity())));
    }

    private Discount discountMultiplyQuantity(Quantity quantity) {
        return new Discount(UNIT_DISCOUNT_AMOUNT.multiplyAmount(quantity.quantity()));
    }
}
