package christmas.promotion.builder;

import christmas.promotion.Promotion;
import java.util.List;

public class PromotionConfig {
    public List<Promotion> promotions =
            new ChristmasPromotionsBuilder()
                    .init("크리스마스 디데이 할인", "2023-12-01", "2023-12-31")
                            .discountIncrease("2023-12-01", 1_000, 100)
                            .and()
                    .and()
            .build();
}
