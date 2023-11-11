package christmas.exception.recoverable;

public class MenuNotExistException extends RecoverableException {
    public MenuNotExistException() {
        super("존재하지 않는 메뉴입니다.");
    }
}
