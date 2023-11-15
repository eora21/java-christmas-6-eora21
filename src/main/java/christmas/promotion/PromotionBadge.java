package christmas.promotion;

import christmas.promotion.information.Benefit;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public enum PromotionBadge {

    NONE("없음", new Benefit(0)),
    STAR("별", new Benefit(5_000)),
    TREE("트리", new Benefit(10_000)),
    SANTA("산타", new Benefit(20_000));

    private final String description;
    private final Benefit benefit;

    PromotionBadge(String description, Benefit benefit) {
        this.description = description;
        this.benefit = benefit;
    }

    public static PromotionBadge getBadge(Benefit benefit) {
        return Arrays.stream(PromotionBadge.values())
                .sorted(Comparator.comparing(promotionBadge -> promotionBadge.benefit))
                .sorted(Comparator.reverseOrder())
                .filter(promotionBadge -> benefit.isGreaterOrEqual(promotionBadge.benefit))
                .findFirst()
                .orElse(NONE);
    }

    public String getDescription() {
        return description;
    }
}
