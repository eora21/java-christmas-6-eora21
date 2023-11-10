package christmas.order;

import christmas.menu.Menu;

public class OrderDetail {
    private final Menu menu;
    private final OrderSize orderSize;

    public OrderDetail(Menu menu, int orderSize) {
        this.menu = menu;
        this.orderSize = new OrderSize(orderSize);
    }

    public Menu getMenu() {
        return menu;
    }

    public OrderSize getOrderSize() {
        return orderSize;
    }
}
