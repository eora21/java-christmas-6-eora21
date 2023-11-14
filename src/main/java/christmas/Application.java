package christmas;

import christmas.planner.PromotionPlaner;
import christmas.view.ConsoleInputView;
import christmas.view.ConsoleOutputView;
import christmas.view.View;

public class Application {
    public static void main(String[] args) {
        View view = new View(new ConsoleInputView(), new ConsoleOutputView());
        PromotionPlaner promotionPlaner = new PromotionPlaner(view, 2023, 12);
        promotionPlaner.runPlaner();
    }
}
