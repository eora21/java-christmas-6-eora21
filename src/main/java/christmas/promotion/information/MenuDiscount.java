package christmas.promotion.information;

import christmas.information.Amount;
import christmas.menu.Menu;
import christmas.order.OrderDetail;
import java.util.EnumMap;
import java.util.Map;

public class MenuDiscount {
    private final Map<Menu, Discount> menuDiscount;

    public MenuDiscount(Map<Menu, Discount> menuDiscount) {
        this.menuDiscount = new EnumMap<>(Menu.class);
        this.menuDiscount.putAll(menuDiscount);
    }

    public Amount calculateMenuDiscount(OrderDetail orderDetail) {
        Discount discount = menuDiscount.getOrDefault(orderDetail.getMenu(), new Discount(Amount.createZeroAmount()));
        return discount.calculateDiscount(orderDetail.calculateOrderDetailAmount());
    }

    public MenuDiscount mergeMenuDiscount(MenuDiscount otherMenuDiscount) {
        otherMenuDiscount.menuDiscount
                .forEach((menu, discount) -> menuDiscount.merge(menu, discount, Discount::sum));
        return this;
    }
}
