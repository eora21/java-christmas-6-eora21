package christmas.planner;

import christmas.exception.alert.AlertException;
import christmas.exception.alert.DateException;
import christmas.view.View;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.function.Supplier;

public class PromotionPlaner {
    private final View view;
    private final Year year;
    private final Month month;

    public PromotionPlaner(View view, int year, int month) {
        this.view = view;
        this.year = Year.of(year);
        this.month = Month.of(month);
    }

    public void runPlaner() {
        view.showGreeting(month);
        LocalDate localDate = repeatWhenEnterCorrectAnswer(this::enterRequireDate);
    }

    private LocalDate enterRequireDate() {
        try {
            return LocalDate.of(year.getValue(), month.getValue(), view.requireVisitDay(month));
        } catch (DateTimeException ignore) {
            throw new DateException();
        }
    }

    private <T> T repeatWhenEnterCorrectAnswer(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (AlertException alertException) {
                view.showExceptionMessage(alertException.getMessage());
            }
        }
    }
}
