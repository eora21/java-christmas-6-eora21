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
import java.util.NoSuchElementException;
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
        Orders orders = repeatWhenEnterCorrectAnswer(this::enterOrders);
        showPromotionResult(localDate, orders);
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

    private Orders enterOrders() {
        try {
            List<OrderRequest> orderRequests = view.requireOrders();
            return Orders.newInstance(orderRequests);
        } catch (RecoverableException | NoSuchElementException ignore) {
            throw new OrderException();
        }
    }

    private void showPromotionResult(LocalDate localDate, Orders orders) {
        Amount beforeDiscountAmount = orders.getOrdersTotalAmount();
        PromotionStatistics promotionStatistics = PromotionPlan.getPromotionStatistics(localDate, orders);
        PromotionBenefitInfo promotionBenefitInfo = promotionStatistics.getPromotionBenefitInfo();
        Benefit totalBenefit = promotionBenefitInfo.getTotalBenefit();

        view.showPreviewBenefitsMessage(localDate);
        view.showOrders(orders);
        view.showBeforeDiscountAmount(beforeDiscountAmount);
        view.showGiveaways(promotionStatistics.getGiveaways());
        view.showPromotionBenefitInfo(promotionBenefitInfo);
        view.showTotalBenefit(totalBenefit);
        view.showAfterDiscountAmount(promotionStatistics.getAfterDiscountAmount());
        view.showBadge(PromotionBadge.getBadge(totalBenefit), month);
    }
}
