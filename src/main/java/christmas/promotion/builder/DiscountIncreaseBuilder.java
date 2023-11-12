package christmas.promotion.builder;

import christmas.information.Amount;
import christmas.order.Orders;
import christmas.promotion.information.Benefit;
import christmas.promotion.information.Discount;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

public class DiscountIncreaseBuilder {
    private static final int EACH_DAY = 1;
    private final ChristmasPromotionBuilder christmasPromotionBuilder;
    private final LocalDate startDate;
    private final Amount startAmount;
    private final Amount increaseAmount;
    private int incrementalCycle = EACH_DAY;

    protected DiscountIncreaseBuilder(ChristmasPromotionBuilder builder, String startDate, int startAmount,
                                      int increaseAmount) {
        this.christmasPromotionBuilder = builder;
        this.startDate = LocalDate.parse(startDate);
        this.startAmount = new Amount(startAmount);
        this.increaseAmount = new Amount(increaseAmount);
    }

    public DiscountIncreaseBuilder setIncrementalCycle(int incrementalCycle) {
        this.incrementalCycle = incrementalCycle;
        return this;
    }

    public ChristmasPromotionBuilder and() {
        christmasPromotionBuilder.add(this::calculateDiscount);
        return christmasPromotionBuilder;
    }

    private Optional<Discount> calculateDiscount(LocalDate localDate, Orders orders) {
        Period between = Period.between(startDate, localDate);
        Amount amount = increaseAmount.multiplyAmount(between.getDays());
        Amount totalAmount = amount.plusAmount(startAmount);
        return Optional.of(new Discount(totalAmount));
    }
}
