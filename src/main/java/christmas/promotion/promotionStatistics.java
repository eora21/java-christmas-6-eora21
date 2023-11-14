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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class promotionStatistics {
    private final List<PromotionPlan> promotionPlans;
    private final Map<Promotion, Giveaway> promotionGiveaway;
    private final Map<Promotion, Map<Menu, Discount>> promotionMenuDiscount;
    private final Map<Promotion, Discount> promotionTotalDiscount;
    private final Amount afterDiscountAmount;

    public promotionStatistics(List<PromotionPlan> promotionPlans, LocalDate localDate, Orders orders) {
        this.promotionPlans = promotionPlans;
        this.promotionGiveaway = calculatePromotionGiveaway(localDate, orders);
        this.promotionMenuDiscount = calculatePromotionMenuDiscount(localDate, orders);
        this.promotionTotalDiscount = calculatePromotionTotalDiscount(localDate, orders);
        this.afterDiscountAmount = calculateAfterDiscountAmount(orders);
    }

    private Map<Promotion, Giveaway> calculatePromotionGiveaway(LocalDate localDate, Orders orders) {
        Map<Promotion, Giveaway> promotionGiveaway = new HashMap<>();

        for (PromotionPlan promotionPlan : promotionPlans) {
            Promotion promotion = promotionPlan.getPromotion();
            promotion.getGiveaway(localDate, orders)
                    .ifPresent(giveaway -> promotionGiveaway.put(promotion, giveaway));
        }

        return promotionGiveaway;
    }

    private Map<Promotion, Map<Menu, Discount>> calculatePromotionMenuDiscount(LocalDate localDate, Orders orders) {
        Map<Promotion, Map<Menu, Discount>> promotionMenuDiscount = new HashMap<>();

        for (PromotionPlan promotionPlan : promotionPlans) {
            Promotion promotion = promotionPlan.getPromotion();
            Map<Menu, Discount> menuDiscount = promotion.calculateMenuDiscount(localDate, orders);
            promotionMenuDiscount.put(promotion, menuDiscount);
        }

        return promotionMenuDiscount;
    }

    private Map<Promotion, Discount> calculatePromotionTotalDiscount(LocalDate localDate, Orders orders) {
        Map<Promotion, Discount> promotionTotalDiscount = new HashMap<>();

        for (PromotionPlan promotionPlan : promotionPlans) {
            Promotion promotion = promotionPlan.getPromotion();
            promotion.calculateTotalDiscount(localDate, orders)
                    .ifPresent(discount -> promotionTotalDiscount.put(promotion, discount));
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

    public List<Giveaway> getGiveaways(Map<Promotion, Giveaway> promotionGiveaway) {
        return promotionGiveaway.values()
                .stream()
                .toList();
    }

    public PromotionBenefitInfo getPromotionBenefitInfo() {
        Map<Promotion, Benefit> promotionBenefit = new HashMap<>();

        mergePromotionGiveawayIntoBenefit(promotionBenefit);
        mergePromotionMenuDiscountIntoBenefit(promotionBenefit);
        mergePromotionTotalDiscountIntoBenefit(promotionBenefit);

        return new PromotionBenefitInfo(promotionBenefit);
    }

    private void mergePromotionGiveawayIntoBenefit(Map<Promotion, Benefit> promotionBenefit) {
        promotionGiveaway.forEach((promotion, giveaway) ->
                promotionBenefit.merge(promotion, giveaway.convertToBenefit(), Benefit::sum));
    }

    private void mergePromotionMenuDiscountIntoBenefit(Map<Promotion, Benefit> promotionBenefit) {
        Map<Promotion, Benefit> promotionMenuBenefit = promotionMenuDiscount.entrySet()
                .stream()
                .collect(Collectors.toUnmodifiableMap(Entry::getKey,
                        entry -> zipMenuDiscountToBenefit(entry.getValue())));

        promotionMenuBenefit.forEach((promotion, benefit) ->
                promotionBenefit.merge(promotion, benefit, Benefit::sum));
    }

    private Benefit zipMenuDiscountToBenefit(Map<Menu, Discount> menuDiscount) {
        return menuDiscount.values()
                .stream()
                .reduce(Discount::sum)
                .orElseGet(() -> new Discount(Amount.createZeroAmount()))
                .convertToBenefit();
    }

    private void mergePromotionTotalDiscountIntoBenefit(Map<Promotion, Benefit> promotionBenefit) {
        promotionTotalDiscount.forEach((promotion, discount) ->
                promotionBenefit.merge(promotion, discount.convertToBenefit(), Benefit::sum));
    }

    public Amount getAfterDiscountAmount() {
        return afterDiscountAmount;
    }
}
