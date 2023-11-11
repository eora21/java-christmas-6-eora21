package christmas.promotion.discount_promotion;

import christmas.promotion.Promotion;
import christmas.promotion.information.Giveaway;
import christmas.order.Orders;
import java.time.LocalDate;
import java.util.Optional;

public interface DiscountPromotion extends Promotion {
    @Override
    default Optional<Giveaway> getGiveaway(LocalDate localDate, Orders orders) {
        return Optional.empty();
    }
}
