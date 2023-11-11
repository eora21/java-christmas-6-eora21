package christmas.promotion;

import christmas.promotion.information.Discount;
import christmas.promotion.information.Giveaway;
import christmas.order.Orders;
import java.time.LocalDate;
import java.util.Optional;

public interface Promotion {
    Optional<Discount> calculateDiscount(LocalDate localDate, Orders orders);
    Optional<Giveaway> getGiveaway(LocalDate localDate, Orders orders);
}
