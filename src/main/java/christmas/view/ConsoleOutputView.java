package christmas.view;

import christmas.information.Amount;
import christmas.information.Quantity;
import christmas.menu.Menu;
import christmas.order.OrderDetail;
import christmas.order.Orders;
import christmas.promotion.PromotionBadge;
import christmas.promotion.PromotionPlan;
import christmas.promotion.information.Benefit;
import christmas.promotion.information.Giveaway;
import christmas.promotion.information.PromotionBenefitInfo;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class ConsoleOutputView implements OutputView {
    private static final String GREETING = "안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다.\n";
    private static final String REQUEST_VISIT_DAY = "%d월 중 식당 예상 방문 날짜는 언제인가요? (%s)\n";
    private static final String REQUEST_ORDERS = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. %s)\n";
    private static final String PREVIEW_BENEFITS = "%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n\n";
    private static final String PRINT_ORDERS = "<주문 메뉴>";
    private static final String MENU_QUANTITY = "%s %d개\n";
    private static final String PRINT_BEFORE_DISCOUNT_AMOUNT = "<할인 전 총주문 금액>";
    private static final String AMOUNT = "%,d원";
    private static final String PRINT_GIVEAWAYS = "<증정 메뉴>";
    private static final String NONE = "없음";
    private static final String PRINT_PROMOTION_BENEFIT_INFO = "<혜택 내역>";
    private static final String PROMOTION_AND_BENEFIT = "%s: " + AMOUNT + "\n";
    private static final String PRINT_TOTAL_BENEFIT = "<총혜택 금액>";
    private static final String PRINT_AFTER_DISCOUNT_AMOUNT = "<할인 후 예상 결제 금액>";
    private static final String PRINT_BADGE = "<%d월 이벤트 배지>\n";
    private static final String ERROR = "[ERROR] ";
    private static final String PRINT_NON_PROMOTION = "혜택을 받기 위해선 최소 " + AMOUNT + " 이상 주문하셔야 합니다.\n\n";

    @Override
    public void printGreeting(Month month) {
        System.out.printf(GREETING, month.getValue());
    }

    @Override
    public void requestVisitDay(Month month, String visitDayForm) {
        System.out.printf(REQUEST_VISIT_DAY, month.getValue(), visitDayForm);
    }

    @Override
    public void requestOrders(String orderRequestsExample) {
        System.out.printf(REQUEST_ORDERS, orderRequestsExample);
    }

    @Override
    public void printPreviewBenefitsMessage(LocalDate localDate) {
        System.out.printf(PREVIEW_BENEFITS, localDate.getMonth().getValue(), localDate.getDayOfMonth());
    }

    @Override
    public void printOrders(Orders orders) {
        System.out.println(PRINT_ORDERS);
        orders.getOrderDetails()
                .forEach(this::printOrderDetail);
        System.out.println();
    }

    private void printOrderDetail(OrderDetail orderDetail) {
        Menu menu = orderDetail.getMenu();
        Quantity quantity = orderDetail.getQuantity();
        System.out.printf(MENU_QUANTITY, menu.getName(), quantity.quantity());
    }

    @Override
    public void printBeforeDiscountAmount(Amount beforeDiscountAmount) {
        System.out.println(PRINT_BEFORE_DISCOUNT_AMOUNT);
        System.out.printf(AMOUNT, beforeDiscountAmount.amount());
        System.out.println();
        System.out.println();
    }

    @Override
    public void printGiveaways(List<Giveaway> giveaways) {
        System.out.println(PRINT_GIVEAWAYS);
        printEachGiveaway(giveaways);
        System.out.println();
    }

    private void printEachGiveaway(List<Giveaway> giveaways) {
        if (giveaways.isEmpty()) {
            System.out.println(NONE);
            return;
        }

        giveaways.forEach(this::printGiveaway);
    }

    private void printGiveaway(Giveaway giveaway) {
        Menu menu = giveaway.getMenu();
        Quantity quantity = giveaway.getQuantity();
        System.out.printf(MENU_QUANTITY, menu.getName(), quantity.quantity());
    }

    @Override
    public void printPromotionBenefitInfo(PromotionBenefitInfo promotionBenefitInfo) {
        System.out.println(PRINT_PROMOTION_BENEFIT_INFO);
        printEachPromotionAndBenefit(promotionBenefitInfo.getPromotionBenefitInfo());
        System.out.println();
    }

    private void printEachPromotionAndBenefit(Map<PromotionPlan, Benefit> promotionAndBenefit) {
        Map<PromotionPlan, Benefit> existBenefitPromotions = promotionAndBenefit.entrySet()
                .stream()
                .filter(entry -> isExistBenefit(entry.getValue()))
                .collect(Collectors.toUnmodifiableMap(Entry::getKey, Entry::getValue));
        printExistBenefitPromotions(existBenefitPromotions);
    }

    private boolean isExistBenefit(Benefit benefit) {
        Amount benefitAmount = benefit.getBenefitAmount();
        return !benefitAmount.isZeroAmount();
    }

    private void printExistBenefitPromotions(Map<PromotionPlan, Benefit> existBenefitPromotions) {
        if (existBenefitPromotions.isEmpty()) {
            System.out.println(NONE);
            return;
        }

        existBenefitPromotions.forEach(this::printPromotionAndBenefit);
    }

    private void printPromotionAndBenefit(PromotionPlan promotionPlan, Benefit benefit) {
        Amount benefitAmount = benefit.getBenefitAmount();
        System.out.printf(PROMOTION_AND_BENEFIT, promotionPlan.getPromotionName(), -benefitAmount.amount());
    }

    @Override
    public void printTotalBenefit(Benefit totalBenefit) {
        System.out.println(PRINT_TOTAL_BENEFIT);
        Amount benefitAmount = totalBenefit.getBenefitAmount();
        System.out.printf(AMOUNT, -benefitAmount.amount());
        System.out.println();
        System.out.println();
    }

    @Override
    public void printAfterDiscountAmount(Amount afterDiscountAmount) {
        System.out.println(PRINT_AFTER_DISCOUNT_AMOUNT);
        System.out.printf(AMOUNT, afterDiscountAmount.amount());
        System.out.println();
        System.out.println();
    }

    @Override
    public void printBadge(PromotionBadge badge, Month month) {
        System.out.printf(PRINT_BADGE, month.getValue());
        System.out.println(badge.getDescription());
    }

    @Override
    public void printExceptionMessage(String message) {
        System.out.println(ERROR + message);
    }

    @Override
    public void printNonPromotion(Amount minPromotionAmount) {
        System.out.printf(PRINT_NON_PROMOTION, minPromotionAmount.amount());
    }
}
