package christmas.exception.non_fatal;

import christmas.menu.MenuGroup;

public class OrderOnlyOneMenuGroupException extends CustomException {
    public OrderOnlyOneMenuGroupException(MenuGroup menuGroup) {
        super(menuGroup.getMenuGroupName() + "만 주문할 수는 없습니다. 다른 그룹의 메뉴를 추가적으로 선택해 주세요.");
    }
}
