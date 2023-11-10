package christmas.exception.custom;

public class OrderOnlyDrinkException extends CustomException {
    public OrderOnlyDrinkException() {
        super("음료만 주문할 수는 없습니다. 다른 메뉴들을 추가적으로 선택해 주세요.");
    }
}
