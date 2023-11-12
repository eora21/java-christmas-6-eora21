package christmas.promotion.builder;

import christmas.promotion.Promotion;
import java.util.ArrayList;
import java.util.List;

public class ChristmasPromotionsBuilder {
    private final List<Promotion> promotions = new ArrayList<>();

    public ChristmasPromotionBuilder init(String name, String startDate, String endDate) {
        return new ChristmasPromotionBuilder(this, name, startDate, endDate);
    }

    public void add(Promotion promotion) {
        promotions.add(promotion);
    }

    public List<Promotion> build() {
        return promotions;
    }
}
