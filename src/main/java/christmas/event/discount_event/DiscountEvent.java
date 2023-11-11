package christmas.event.discount_event;

import christmas.event.Event;
import christmas.event.information.Giveaway;
import christmas.order.Orders;
import java.time.LocalDate;
import java.util.Optional;

public interface DiscountEvent extends Event {
    @Override
    default Optional<Giveaway> getGiveaway(LocalDate localDate, Orders orders) {
        return Optional.empty();
    }
}
