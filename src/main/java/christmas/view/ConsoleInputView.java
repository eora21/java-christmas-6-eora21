package christmas.view;

import static camp.nextstep.edu.missionutils.Console.readLine;

import christmas.order.OrderRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class ConsoleInputView implements InputView {
    private static final String VISIT_DAY_FORM = "숫자만 입력해 주세요!";
    private static final String ORDER_REQUEST_DELIMITER = ",";
    private static final String NAME_QUANTITY_DELIMITER = "-";

    @Override
    public String requireVisitDayForm() {
        return VISIT_DAY_FORM;
    }

    @Override
    public int enterVisitDay() {
        return Integer.parseInt(readLine());
    }

    @Override
    public String requireOrdersExample() {
        return new StringJoiner(ORDER_REQUEST_DELIMITER)
                .add(createExampleOrderRequest("해산물파스타", 2))
                .add(createExampleOrderRequest("레드와인", 1))
                .add(createExampleOrderRequest("초코케이크", 1))
                .toString();
    }

    private String createExampleOrderRequest(String name, int quantity) {
        return name + NAME_QUANTITY_DELIMITER + quantity;
    }

    @Override
    public List<OrderRequest> enterOrders() {
        return null;
    }
}
