package christmas.view;

import christmas.information.Amount;
import christmas.order.Orders;
import christmas.promotion.PromotionBadge;
import christmas.promotion.information.Benefit;
import christmas.promotion.information.Giveaway;
import christmas.promotion.information.PromotionBenefitInfo;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class ConsoleOutputView implements OutputView {
    private static final String ERROR = "[ERROR] ";
    @Override
    public void printGreeting(Month month) {
        System.out.println("안녕하세요! 우테코 식당 " + month.getValue() + " 월 이벤트 플래너입니다.");
    }

    @Override
    public void requestVisitDay(Month month, String visitDayForm) {
        System.out.println(month.getValue() + "월 중 식당 예상 방문 날짜는 언제인가요? (" + visitDayForm + ")");
    }

    @Override
    public void printPreviewBenefitsMessage(LocalDate localDate) {

    }

    @Override
    public void requestOrders(String orderRequestsExample) {

    }

    @Override
    public void printOrders(Orders orders) {

    }

    @Override
    public void printBeforeDiscountAmount(Amount beforeDiscountAmount) {

    }

    @Override
    public void printGiveaways(List<Giveaway> giveaways) {

    }

    @Override
    public void printPromotionBenefitInfo(PromotionBenefitInfo promotionBenefitInfo) {

    }

    @Override
    public void printTotalBenefit(Benefit totalBenefit) {

    }

    @Override
    public void printAfterDiscountAmount(Amount afterDiscountAmount) {

    }

    @Override
    public void printBadge(PromotionBadge badge) {

    }

    @Override
    public void printExceptionMessage(String message) {
        System.out.println(ERROR + message);
    }
}
