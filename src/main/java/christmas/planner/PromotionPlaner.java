package christmas.planner;

import christmas.exception.alert.AlertException;
import christmas.exception.alert.DateException;
import christmas.exception.alert.OrderException;
import christmas.exception.recoverable.RecoverableException;
import christmas.information.Amount;
import christmas.order.OrderRequest;
import christmas.order.Orders;
import christmas.promotion.PromotionBadge;
import christmas.promotion.PromotionPlan;
import christmas.promotion.PromotionStatistics;
import christmas.promotion.information.Benefit;
import christmas.promotion.information.PromotionBenefitInfo;
import christmas.view.View;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.function.Supplier;

public class PromotionPlaner {
    private static final Amount MIN_PROMOTION_AMOUNT = new Amount(10_000);
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
        Orders orders = repeatWhenEnterCorrectAnswer(this::enterOrders);
        showOrdersResult(localDate, orders);
    }

    private LocalDate enterRequireDate() {
        try {
            return LocalDate.of(year.getValue(), month.getValue(), view.requireVisitDay(month));
        } catch (RecoverableException | DateTimeException ignore) {
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

    private Orders enterOrders() {
        try {
            List<OrderRequest> orderRequests = view.requireOrders();
            return Orders.newInstance(orderRequests);
        } catch (RecoverableException ignore) {
            throw new OrderException();
        }
    }

    private void showOrdersResult(LocalDate localDate, Orders orders) {
        view.showPreviewBenefitsMessage(localDate);
        view.showOrders(orders);
        view.showPreviewBenefitsMessage(localDate);
        view.showOrders(orders);

        PromotionStatistics promotionStatistics = getPromotionStatisticsByTotalAmount(localDate, orders);
        showPromotionResult(promotionStatistics);
    }

    private PromotionStatistics getPromotionStatisticsByTotalAmount(LocalDate localDate, Orders orders) {
        Amount totalAmount = orders.getOrdersTotalAmount();
        view.showBeforeDiscountAmount(totalAmount);

        if (totalAmount.isGreaterOrEqual(MIN_PROMOTION_AMOUNT)) {
            return PromotionPlan.getPromotionStatistics(localDate, orders);
        }

        view.showNonPromotion(MIN_PROMOTION_AMOUNT);
        return PromotionStatistics.emptyInstance(localDate, orders);
    }

    private void showPromotionResult(PromotionStatistics promotionStatistics) {
        PromotionBenefitInfo promotionBenefitInfo = promotionStatistics.getPromotionBenefitInfo();
        Benefit totalBenefit = promotionBenefitInfo.getTotalBenefit();

        view.showGiveaways(promotionStatistics.getGiveaways());
        view.showPromotionBenefitInfo(promotionBenefitInfo);
        view.showTotalBenefit(totalBenefit);
        view.showAfterDiscountAmount(promotionStatistics.getAfterDiscountAmount());
        view.showBadge(PromotionBadge.getBadge(totalBenefit), month);
    }
}
