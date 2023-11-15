package christmas.promotion;

import christmas.information.Amount;
import christmas.menu.Menu;
import christmas.order.Orders;
import christmas.promotion.information.Benefit;
import christmas.promotion.information.Discount;
import christmas.promotion.information.Giveaway;
import christmas.promotion.information.MenuDiscount;
import christmas.promotion.information.PromotionBenefitInfo;
import java.time.LocalDate;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class PromotionStatistics {
    private final List<PromotionPlan> promotionPlans;
    private final Map<PromotionPlan, Giveaway> promotionGiveaway;
    private final Map<PromotionPlan, Map<Menu, Discount>> promotionMenuDiscount;
    private final Map<PromotionPlan, Discount> promotionTotalDiscount;
    private final Amount afterDiscountAmount;

    public PromotionStatistics(List<PromotionPlan> promotionPlans, LocalDate localDate, Orders orders) {
        this.promotionPlans = promotionPlans;
        this.promotionGiveaway = calculatePromotionGiveaway(localDate, orders);
        this.promotionMenuDiscount = calculatePromotionMenuDiscount(localDate, orders);
        this.promotionTotalDiscount = calculatePromotionTotalDiscount(localDate, orders);
        this.afterDiscountAmount = calculateAfterDiscountAmount(orders);
    }

    private Map<PromotionPlan, Giveaway> calculatePromotionGiveaway(LocalDate localDate, Orders orders) {
        Map<PromotionPlan, Giveaway> promotionGiveaway = new EnumMap<>(PromotionPlan.class);

        for (PromotionPlan promotionPlan : promotionPlans) {
            Promotion promotion = promotionPlan.getPromotion();
            promotion.getGiveaway(localDate, orders)
                    .ifPresent(giveaway -> promotionGiveaway.put(promotionPlan, giveaway));
        }

        return promotionGiveaway;
    }

    private Map<PromotionPlan, Map<Menu, Discount>> calculatePromotionMenuDiscount(LocalDate localDate, Orders orders) {
        Map<PromotionPlan, Map<Menu, Discount>> promotionMenuDiscount = new EnumMap<>(PromotionPlan.class);

        for (PromotionPlan promotionPlan : promotionPlans) {
            Promotion promotion = promotionPlan.getPromotion();
            Map<Menu, Discount> menuDiscount = promotion.calculateMenuDiscount(localDate, orders);
            promotionMenuDiscount.put(promotionPlan, menuDiscount);
        }

        return promotionMenuDiscount;
    }

    private Map<PromotionPlan, Discount> calculatePromotionTotalDiscount(LocalDate localDate, Orders orders) {
        Map<PromotionPlan, Discount> promotionTotalDiscount = new EnumMap<>(PromotionPlan.class);

        for (PromotionPlan promotionPlan : promotionPlans) {
            Promotion promotion = promotionPlan.getPromotion();
            promotion.calculateTotalDiscount(localDate, orders)
                    .ifPresent(discount -> promotionTotalDiscount.put(promotionPlan, discount));
        }

        return promotionTotalDiscount;
    }

    public Amount calculateAfterDiscountAmount(Orders orders) {
        MenuDiscount menuDiscount = zipPromotionMenuDiscount();
        Discount totalDiscount = zipPromotionTotalDiscount();
        return orders.calculateAllDiscount(menuDiscount, totalDiscount);
    }

    private MenuDiscount zipPromotionMenuDiscount() {
        return promotionMenuDiscount.values()
                .stream()
                .map(MenuDiscount::new)
                .reduce(MenuDiscount::mergeMenuDiscount)
                .orElseGet(() -> new MenuDiscount(Collections.emptyMap()));
    }

    private Discount zipPromotionTotalDiscount() {
        return promotionTotalDiscount.values()
                .stream()
                .reduce(Discount::sum)
                .orElseGet(() -> new Discount(Amount.createZeroAmount()));
    }

    public List<Giveaway> getGiveaways() {
        return promotionGiveaway.values()
                .stream()
                .toList();
    }

    public PromotionBenefitInfo getPromotionBenefitInfo() {
        Map<PromotionPlan, Benefit> promotionBenefit = new EnumMap<>(PromotionPlan.class);

        mergePromotionGiveawayIntoBenefit(promotionBenefit);
        mergePromotionMenuDiscountIntoBenefit(promotionBenefit);
        mergePromotionTotalDiscountIntoBenefit(promotionBenefit);

        return new PromotionBenefitInfo(promotionBenefit);
    }

    private void mergePromotionGiveawayIntoBenefit(Map<PromotionPlan, Benefit> promotionBenefit) {
        promotionGiveaway.forEach((promotionPlan, giveaway) ->
                promotionBenefit.merge(promotionPlan, giveaway.convertToBenefit(), Benefit::sum));
    }

    private void mergePromotionMenuDiscountIntoBenefit(Map<PromotionPlan, Benefit> promotionBenefit) {
        Map<PromotionPlan, Benefit> promotionMenuBenefit = promotionMenuDiscount.entrySet()
                .stream()
                .collect(Collectors.toUnmodifiableMap(Entry::getKey,
                        entry -> zipMenuDiscountToBenefit(entry.getValue())));

        promotionMenuBenefit.forEach((promotionPlan, benefit) ->
                promotionBenefit.merge(promotionPlan, benefit, Benefit::sum));
    }

    private Benefit zipMenuDiscountToBenefit(Map<Menu, Discount> menuDiscount) {
        return menuDiscount.values()
                .stream()
                .reduce(Discount::sum)
                .orElseGet(() -> new Discount(Amount.createZeroAmount()))
                .convertToBenefit();
    }

    private void mergePromotionTotalDiscountIntoBenefit(Map<PromotionPlan, Benefit> promotionBenefit) {
        promotionTotalDiscount.forEach((promotionPlan, discount) ->
                promotionBenefit.merge(promotionPlan, discount.convertToBenefit(), Benefit::sum));
    }

    public Amount getAfterDiscountAmount() {
        return afterDiscountAmount;
    }
}
