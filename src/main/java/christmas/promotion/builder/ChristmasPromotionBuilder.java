package christmas.promotion.builder;

import christmas.order.Orders;
import christmas.promotion.Promotion;
import christmas.promotion.discount_promotion.ChristmasDdayPromotion;
import christmas.promotion.discount_promotion.DiscountPromotion;
import christmas.promotion.information.Benefit;
import christmas.promotion.information.Discount;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

public class ChristmasPromotionBuilder {
    private final ChristmasPromotionsBuilder christmasPromotionsBuilder;
    private final String promotionName;
    private final LocalDate promotionStartDate;
    private final LocalDate promotionEndDate;
    private BiFunction<LocalDate, Orders, Optional<Discount>> promotionLogics;

    public ChristmasPromotionBuilder(ChristmasPromotionsBuilder upperBuilder, String promotionName,
                                     String promotionStartDate, String promotionEndDate) {
        christmasPromotionsBuilder = upperBuilder;
        this.promotionName = promotionName;
        this.promotionStartDate = LocalDate.parse(promotionStartDate);
        this.promotionEndDate = LocalDate.parse(promotionEndDate);
    }

    public DiscountIncreaseBuilder discountIncrease(String startDate, int startAmount, int increaseAmount) {
        return new DiscountIncreaseBuilder(this, startDate, startAmount, increaseAmount);
    }

    public ChristmasPromotionsBuilder and() {
        christmasPromotionsBuilder.add(this.build());
        return christmasPromotionsBuilder;
    }

    public Promotion build() {
        return (DiscountPromotion) (localDate, orders) -> promotionLogics.apply(localDate, orders);
    }

    public void add(BiFunction<LocalDate, Orders, Optional<Discount>> logic) {
        promotionLogics = logic;
    }
}
