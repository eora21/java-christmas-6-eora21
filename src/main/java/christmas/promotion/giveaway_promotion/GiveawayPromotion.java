package christmas.promotion.giveaway_promotion;

import christmas.order.Orders;
import christmas.promotion.Promotion;
import christmas.promotion.information.Discount;
import java.time.LocalDate;
import java.util.Optional;

public interface GiveawayPromotion extends Promotion {
    @Override
    default Optional<Discount> calculateDiscount(LocalDate localDate, Orders orders) {
        return Optional.empty();
    }
}
