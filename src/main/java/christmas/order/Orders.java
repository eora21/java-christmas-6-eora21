package christmas.order;

import christmas.exception.alert.OrderOnlyOneMenuGroupException;
import christmas.exception.recoverable.OrderEmptyException;
import christmas.exception.recoverable.OrderMenuDuplicateException;
import christmas.exception.alert.OverMaxTotalOrderQuantityException;
import christmas.information.Amount;
import christmas.information.Quantity;
import christmas.menu.Menu;
import christmas.menu.MenuGroup;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Orders {
    private static final int MAX_TOTAL_ORDER_QUANTITY = 20;
    private static final MenuGroup NOT_ONLY_MENU_GROUP = MenuGroup.DRINK;
    private final List<OrderDetail> orderDetails;

    public Orders(List<OrderDetail> orderDetails) {
        validate(orderDetails);
        this.orderDetails = new ArrayList<>(orderDetails);
    }

    private void validate(List<OrderDetail> orderDetails) {
        validateEmptyOrder(orderDetails);
        validateTotalOrderSize(orderDetails);
        validateOrderMenuDuplicate(orderDetails);
        validateOrderMenuGroup(orderDetails);
    }

    private void validateEmptyOrder(List<OrderDetail> orderDetails) {
        if (orderDetails.isEmpty()) {
            throw new OrderEmptyException();
        }
    }

    private void validateTotalOrderSize(List<OrderDetail> orderDetails) {
        int totalOrderQuantity = orderDetails.stream()
                .map(OrderDetail::getQuantity)
                .mapToInt(Quantity::quantity)
                .sum();

        if (MAX_TOTAL_ORDER_QUANTITY < totalOrderQuantity) {
            throw new OverMaxTotalOrderQuantityException(MAX_TOTAL_ORDER_QUANTITY);
        }
    }

    private void validateOrderMenuDuplicate(List<OrderDetail> orderDetails) {
        Set<Menu> orderMenuGroup = orderDetails.stream()
                .map(OrderDetail::getMenu)
                .collect(Collectors.toUnmodifiableSet());

        if (orderDetails.size() != orderMenuGroup.size()) {
            throw new OrderMenuDuplicateException();
        }
    }

    private void validateOrderMenuGroup(List<OrderDetail> orderDetails) {
        orderDetails.stream()
                .map(OrderDetail::getMenu)
                .map(Menu::getMenuGroup)
                .filter(menuGroup -> menuGroup != NOT_ONLY_MENU_GROUP)
                .findAny()
                .orElseThrow(() -> new OrderOnlyOneMenuGroupException(NOT_ONLY_MENU_GROUP));
    }

    public Amount getOrdersTotalAmount() {
        return orderDetails.stream()
                .map(OrderDetail::calculateOrderDetailAmount)
                .reduce(Amount::plusAmount)
                .orElseGet(Amount::createZeroAmount);
    }

    public int getTotalMenuGroupQuantity(MenuGroup menuGroup) {
        return orderDetails.stream()
                .filter(orderDetail -> orderDetail.getMenu().getMenuGroup() == menuGroup)
                .map(OrderDetail::getQuantity)
                .mapToInt(Quantity::quantity)
                .sum();
    }

    public List<OrderDetail> getOrderDetails() {
        return Collections.unmodifiableList(orderDetails);
    }
}
