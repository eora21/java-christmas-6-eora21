package christmas.order;

import christmas.information.Amount;
import christmas.information.Quantity;
import christmas.menu.Menu;

public class OrderDetail {
    private final Menu menu;
    private final Quantity quantity;

    public OrderDetail(Menu menu, int quantity) {
        this.menu = menu;
        this.quantity = new Quantity(quantity);
    }

    public Amount calculateOrderDetailAmount() {
        Amount amount = menu.getAmount();
        return amount.multiplyAmount(quantity.quantity());
    }

    public Menu getMenu() {
        return menu;
    }

    public Quantity getQuantity() {
        return quantity;
    }
}
