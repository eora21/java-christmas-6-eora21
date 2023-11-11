package christmas.promotion.information;

import java.time.LocalDate;

public record PromotionTimeFrame(LocalDate startDate, LocalDate endDate) {
    public boolean isDateInRange(LocalDate localDate) {
        if (startDate.isAfter(localDate) || endDate.isBefore(localDate)) {
            return false;
        }

        return true;
    }
}
