package christmas.view;

import christmas.order.OrderRequest;
import java.util.List;

public interface InputView {
    int enterVisitDay();

    List<OrderRequest> enterOrderRequests();
}
