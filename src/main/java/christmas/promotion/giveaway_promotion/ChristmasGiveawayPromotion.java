package christmas.promotion.giveaway_promotion;

import christmas.information.Amount;
import christmas.information.Quantity;
import christmas.menu.Menu;
import christmas.order.Orders;
import christmas.promotion.information.Giveaway;
import java.time.LocalDate;
import java.util.Optional;

public class ChristmasGiveawayPromotion implements GiveawayPromotion {
    private static final Giveaway GIVEAWAY_PROMOTION = new Giveaway(Menu.CHAMPAGNE, new Quantity(1));
    private static final Amount GIVEAWAY_MIN_AMOUNT = new Amount(120_000);

    @Override
    public Optional<Giveaway> getGiveaway(LocalDate localDate, Orders orders) {
        if (moreThanOrEqualToGiveawayMinAmount(orders.getOrdersTotalAmount())) {
            return Optional.of(GIVEAWAY_PROMOTION);
        }

        return Optional.empty();
    }

    private boolean moreThanOrEqualToGiveawayMinAmount(Amount ordersTotalAmount) {
        return GIVEAWAY_MIN_AMOUNT.compareTo(ordersTotalAmount) <= 0;
    }
}
