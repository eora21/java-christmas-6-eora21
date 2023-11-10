package christmas.order;

import christmas.menu.Menu;
import java.util.List;

public class OrderService {
    public Orders placeAnOrder(List<OrderRequest> orderRequests) {
        List<OrderDetail> orderDetails = orderRequests.stream()
                .map(this::createOrderDetail)
                .toList();

        return new Orders(orderDetails);
    }

    private OrderDetail createOrderDetail(OrderRequest orderRequest) {
        Menu menu = Menu.findMenu(orderRequest.orderMenuName());
        return new OrderDetail(menu, orderRequest.orderQuantity());
    }
}
