package christmas.order;

import christmas.information.Quantity;
import christmas.menu.Menu;

public class OrderDetail {
    private final Menu menu;
    private final Quantity quantity;

    public OrderDetail(Menu menu, int quantity) {
        this.menu = menu;
        this.quantity = new Quantity(quantity);
    }

    public Menu getMenu() {
        return menu;
    }

    public Quantity getQuantity() {
        return quantity;
    }
}
