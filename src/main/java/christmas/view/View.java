package christmas.view;

import java.time.Month;

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
}
