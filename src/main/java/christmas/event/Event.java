package christmas.event;

import christmas.event.information.Discount;
import christmas.event.information.Giveaway;
import christmas.order.Orders;
import java.time.LocalDate;
import java.util.Optional;

public interface Event {
    Optional<Discount> calculateDiscount(LocalDate localDate, Orders orders);
    Optional<Giveaway> getGiveaway(LocalDate localDate, Orders orders);
}
