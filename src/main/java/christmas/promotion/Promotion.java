package christmas.promotion;

import christmas.menu.Menu;
import christmas.promotion.information.Discount;
import christmas.promotion.information.Giveaway;
import christmas.order.Orders;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

public interface Promotion {
    default Map<Menu, Discount> calculateMenuDiscount(LocalDate localDate, Orders orders) {
        return Collections.emptyMap();
    }
    default Optional<Discount> calculateTotalDiscount(LocalDate localDate, Orders orders) {
        return Optional.empty();
    }
    default Optional<Giveaway> getGiveaway(LocalDate localDate, Orders orders) {
        return Optional.empty();
    }
}
