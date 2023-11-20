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

public interface OutputView {
    void printGreeting(Month month);

    void requestVisitDay(Month month, String visitDayForm);

    void printPreviewBenefitsMessage(LocalDate localDate);

    void requestOrders(String orderRequestsExample);

    void printOrders(Orders orders);

    void printBeforeDiscountAmount(Amount beforeDiscountAmount);

    void printGiveaways(List<Giveaway> giveaways);

    void printPromotionBenefitInfo(PromotionBenefitInfo promotionBenefitInfo);

    void printTotalBenefit(Benefit totalBenefit);

    void printAfterDiscountAmount(Amount afterDiscountAmount);

    void printBadge(PromotionBadge badge, Month month);

    void printExceptionMessage(String message);

    void printNonPromotion(Amount minPromotionAmount);
}
