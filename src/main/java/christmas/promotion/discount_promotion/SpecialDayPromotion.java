package christmas.promotion.discount_promotion;

import christmas.information.Amount;
import christmas.order.Orders;
import christmas.promotion.information.Discount;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class SpecialDayPromotion implements DiscountPromotion {
    private static final Amount DISCOUNT_AMOUNT = new Amount(1_000);
    private static final List<LocalDate> DISCOUNT_STAR_DATES = List.of(
            LocalDate.parse("2023-12-03"), LocalDate.parse("2023-12-10"), LocalDate.parse("2023-12-17"),
            LocalDate.parse("2023-12-24"), LocalDate.parse("2023-12-25"), LocalDate.parse("2023-12-31"));

    @Override
    public Optional<Discount> calculateDiscount(LocalDate localDate, Orders orders) {
        if (DISCOUNT_STAR_DATES.contains(localDate)) {
            return Optional.of(new Discount(DISCOUNT_AMOUNT));
        }
        return Optional.empty();
    }
}
