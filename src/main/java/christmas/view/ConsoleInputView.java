package christmas.view;

import static camp.nextstep.edu.missionutils.Console.readLine;

import christmas.exception.recoverable.MismatchOrderRequestException;
import christmas.exception.recoverable.ParseIntException;
import christmas.order.OrderRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class ConsoleInputView implements InputView {
    private static final String VISIT_DAY_FORM = "숫자만 입력해 주세요!";
    private static final String ORDER_REQUEST_DELIMITER = ",";
    private static final String NAME_QUANTITY_DELIMITER = "-";
    private static final int NAME_QUANTITY_TOKEN_SIZE = 2;

    @Override
    public String requireVisitDayForm() {
        return VISIT_DAY_FORM;
    }

    @Override
    public int enterVisitDay() {
        return parseInt(readLine());
    }

    private int parseInt(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException ignore) {
            throw new ParseIntException();
        }
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
        StringTokenizer readLineTokenizer = new StringTokenizer(readLine(), ORDER_REQUEST_DELIMITER);

        List<OrderRequest> orderRequests = new ArrayList<>();

        while (readLineTokenizer.hasMoreTokens()) {
            String orderRequestToken = readLineTokenizer.nextToken().trim();
            OrderRequest orderRequest = convertTokenToOrderRequest(orderRequestToken);
            orderRequests.add(orderRequest);
        }

        return orderRequests;
    }

    private OrderRequest convertTokenToOrderRequest(String token) {
        StringTokenizer orderRequestTokenizer = new StringTokenizer(token, NAME_QUANTITY_DELIMITER);

        if (orderRequestTokenizer.countTokens() != NAME_QUANTITY_TOKEN_SIZE) {
            throw new MismatchOrderRequestException();
        }

        String orderMenuName = orderRequestTokenizer.nextToken().trim();
        int orderQuantity = parseInt(orderRequestTokenizer.nextToken().trim());
        return new OrderRequest(orderMenuName, orderQuantity);
    }
}
