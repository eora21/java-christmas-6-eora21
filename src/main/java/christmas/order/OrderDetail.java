package christmas.order;

import christmas.menu.Menu;

public class OrderDetail {
    private final Menu menu;
    private final OrderQuantity orderQuantity;

    public OrderDetail(Menu menu, int orderQuantity) {
        this.menu = menu;
        this.orderQuantity = new OrderQuantity(orderQuantity);
    }

    public Menu getMenu() {
        return menu;
    }

    public OrderQuantity getOrderQuantity() {
        return orderQuantity;
    }
}
