package christmas.promotion.discount_promotion;

import christmas.promotion.information.Discount;
import christmas.information.Amount;
import christmas.order.Orders;
import christmas.promotion.information.PromotionTimeFrame;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

public class ChristmasDdayPromotion implements DiscountPromotion {
    private ChristmasDdayPromotion() {
    }

    private static class PromotionInitializer {
        private static final ChristmasDdayPromotion INSTANCE = new ChristmasDdayPromotion();
    }

    public static ChristmasDdayPromotion getInstance() {
        return PromotionInitializer.INSTANCE;
    }

    private static final PromotionTimeFrame PROMOTION_TIME_FRAME =
            new PromotionTimeFrame(LocalDate.parse("2023-12-01"), LocalDate.parse("2023-12-25"));
    private static final Amount PROMOTION_START_AMOUNT = new Amount(1_000);

    private static final Amount PROMOTION_PLUS_EACH_DAY_AMOUNT = new Amount(100);

    @Override
    public Optional<Discount> calculateDiscount(LocalDate localDate, Orders orders) {
        if (PROMOTION_TIME_FRAME.isDateInRange(localDate)) {
            return calculateDiscount(localDate);
        }

        return Optional.empty();
    }

    private Optional<Discount> calculateDiscount(LocalDate localDate) {
        Period between = Period.between(PROMOTION_TIME_FRAME.startDate(), localDate);
        Amount amount = PROMOTION_PLUS_EACH_DAY_AMOUNT.multiplyAmount(between.getDays());
        Amount totalAmount = amount.plusAmount(PROMOTION_START_AMOUNT);
        return Optional.of(new Discount(totalAmount));
    }
}
