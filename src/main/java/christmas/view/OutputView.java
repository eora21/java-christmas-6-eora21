package christmas.view;

import christmas.information.Amount;
import christmas.order.Orders;
import christmas.promotion.PromotionBadge;
import christmas.promotion.information.Benefit;
import christmas.promotion.information.Giveaway;
import christmas.promotion.information.PromotionBenefitInfo;
import java.util.List;

public interface OutputView {
    void printGreeting();

    void requestVisitDay();

    void requestOrderRequests();

    void printOrders(Orders orders);

    void printBeforeDiscountAmount(Amount beforeDiscountAmount);

    void printGiveaways(List<Giveaway> giveaways);

    void printPromotionBenefitInfo(PromotionBenefitInfo promotionBenefitInfo);

    void printTotalBenefit(Benefit totalBenefit);

    void printAfterDiscountAmount(Amount afterDiscountAmount);

    void printBadge(PromotionBadge badge);
}
