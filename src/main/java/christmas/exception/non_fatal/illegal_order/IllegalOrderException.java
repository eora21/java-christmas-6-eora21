package christmas.exception.non_fatal.illegal_order;

import christmas.exception.non_fatal.CustomException;

public abstract class IllegalOrderException extends CustomException {
    public IllegalOrderException() {
        super("유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}
