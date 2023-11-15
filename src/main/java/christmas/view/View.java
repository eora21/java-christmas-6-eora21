package christmas.view;

import christmas.information.Amount;
import christmas.order.OrderRequest;
import christmas.order.Orders;
import christmas.promotion.PromotionBadge;
import christmas.promotion.information.Benefit;
import christmas.promotion.information.Giveaway;
import christmas.promotion.information.PromotionBenefitInfo;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

public class View {
    private final InputView inputView;
    private final OutputView outputView;

    public View(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void showGreeting(Month month) {
        outputView.printGreeting(month);
    }

    public int requireVisitDay(Month month) {
        outputView.requestVisitDay(month, inputView.requireVisitDayForm());
        return inputView.enterVisitDay();
    }

    public void showExceptionMessage(String message) {
        outputView.printExceptionMessage(message);
    }

    public List<OrderRequest> requireOrders() {
        outputView.requestOrders(inputView.requireOrdersExample());
        return inputView.enterOrders();
    }

    public void showPreviewBenefitsMessage(LocalDate localDate) {
        outputView.printPreviewBenefitsMessage(localDate);
    }

    public void showOrders(Orders orders) {
        outputView.printOrders(orders);
    }

    public void showBeforeDiscountAmount(Amount beforeDiscountAmount) {
        outputView.printBeforeDiscountAmount(beforeDiscountAmount);
    }

    public void showGiveaways(List<Giveaway> giveaways) {
        outputView.printGiveaways(giveaways);
    }

    public void showPromotionBenefitInfo(PromotionBenefitInfo promotionBenefitInfo) {
        outputView.printPromotionBenefitInfo(promotionBenefitInfo);
    }

    public void showTotalBenefit(Benefit totalBenefit) {
        outputView.printTotalBenefit(totalBenefit);
    }

    public void showAfterDiscountAmount(Amount afterDiscountAmount) {
        outputView.printAfterDiscountAmount(afterDiscountAmount);
    }

    public void showBadge(PromotionBadge badge, Month month) {
        outputView.printBadge(badge, month);
    }
}
