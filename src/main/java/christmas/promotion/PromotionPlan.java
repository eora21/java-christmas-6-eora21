package christmas.promotion;

import static java.time.DayOfWeek.*;

import christmas.menu.MenuGroup;
import christmas.promotion.discount_promotion.ChristmasDdayPromotion;
import christmas.promotion.discount_promotion.DayOfTheWeekPromotion;
import christmas.promotion.discount_promotion.SpecialDayPromotion;
import christmas.promotion.giveaway_promotion.ChristmasGiveawayPromotion;
import christmas.promotion.information.PromotionTimeFrame;
import java.time.LocalDate;
import java.util.List;

public enum PromotionPlan {
    CHRIST_MAS_D_DAY_PROMOTION("2023-12-01", "2023-12-25",
            "크리스마스 디데이 할인",
            new ChristmasDdayPromotion()),
    WEEKDAY_PROMOTION("2023-12-01", "2023-12-31",
            "평일 할인",
            new DayOfTheWeekPromotion(List.of(SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY), MenuGroup.DESSERT)),
    WEEKEND_PROMOTION("2023-12-01", "2023-12-31",
            "주말 할인",
            new DayOfTheWeekPromotion(List.of(FRIDAY, SATURDAY), MenuGroup.MAIN)),
    SPECIAL_DAY_PROMOTION("2023-12-01", "2023-12-31",
            "특별 할인",
            new SpecialDayPromotion()),
    CHRISTMAS_GIVEAWAY_PROMOTION("2023-12-01", "2023-12-31",
            "증정 이벤트",
            new ChristmasGiveawayPromotion());

    private final PromotionTimeFrame promotionTimeFrame;
    private final String eventName;
    private final Promotion promotion;

    PromotionPlan(String startDate, String endDate, String eventName, Promotion promotion) {
        LocalDate promotionStartDate = LocalDate.parse(startDate);
        LocalDate promotionEndDate = LocalDate.parse(endDate);

        this.promotionTimeFrame = new PromotionTimeFrame(promotionStartDate, promotionEndDate);
        this.eventName = eventName;
        this.promotion = promotion;
    }

    public Promotion getPromotion() {
        return promotion;
    }
}