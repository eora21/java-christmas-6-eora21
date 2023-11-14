package christmas.view;

import static camp.nextstep.edu.missionutils.Console.readLine;

import christmas.order.OrderRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class ConsoleInputView implements InputView {
    private static final String VISIT_DAY_FORM = "숫자만 입력해 주세요!";

    @Override
    public String requireVisitDayForm() {
        return VISIT_DAY_FORM;
    }

    @Override
    public int enterVisitDay() {
        return 0;
    }

    @Override
    public String requireOrderRequestsExample() {
        return null;
    }

    @Override
    public List<OrderRequest> enterOrderRequests() {
        return null;
    }
}
