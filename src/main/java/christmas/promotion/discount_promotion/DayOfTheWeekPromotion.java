package christmas.promotion.discount_promotion;

import christmas.information.Amount;
import christmas.menu.MenuGroup;
import christmas.order.Orders;
import christmas.promotion.information.Discount;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class DayOfTheWeekPromotion implements DiscountPromotion {
    private static final Amount UNIT_DISCOUNT_AMOUNT = new Amount(2_023);
    private final List<DayOfWeek> dayOfWeeks;
    private final MenuGroup menuGroup;

    public DayOfTheWeekPromotion(List<DayOfWeek> dayOfWeeks, MenuGroup menuGroup) {
        this.dayOfWeeks = dayOfWeeks;
        this.menuGroup = menuGroup;
    }

    @Override
    public Optional<Discount> calculateDiscount(LocalDate localDate, Orders orders) {
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();

        if (dayOfWeeks.contains(dayOfWeek)) {
            return calculateDiscount(orders);
        }

        return Optional.empty();
    }

    private Optional<Discount> calculateDiscount(Orders orders) {
        int totalMenuGroupQuantity = orders.getTotalMenuGroupQuantity(menuGroup);
        Amount discountAmount = UNIT_DISCOUNT_AMOUNT.multiplyAmount(totalMenuGroupQuantity);
        return Optional.of(new Discount(discountAmount));
    }
}
