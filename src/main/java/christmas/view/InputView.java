package christmas.view;

import christmas.order.OrderRequest;
import java.util.List;

public interface InputView {
    String requireVisitDayForm();

    int enterVisitDay();

    String requireOrderRequestsExample();

    List<OrderRequest> enterOrderRequests();
}
