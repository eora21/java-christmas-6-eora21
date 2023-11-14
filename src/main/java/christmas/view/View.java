package christmas.view;

import christmas.order.OrderRequest;
import christmas.order.Orders;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class View {
    private final InputView inputView;
    private final OutputView outputView;

    public View(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void showGreeting(Month month) {
        outputView.printGreeting(month);
    }

    public int requireVisitDay(Month month) {
        outputView.requestVisitDay(month, inputView.requireVisitDayForm());
        return inputView.enterVisitDay();
    }

    public void showExceptionMessage(String message) {
        outputView.printExceptionMessage(message);
    }

    public List<OrderRequest> requireOrders() {
        outputView.requestOrders(inputView.requireOrdersExample());
        return inputView.enterOrders();
    }

    public void showPreviewBenefitsMessage(LocalDate localDate) {
        outputView.printPreviewBenefitsMessage(localDate);
    }

    public void showOrders(Orders orders) {
        outputView.printOrders(orders);
    }
}
