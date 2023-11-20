package christmas.menu;

public enum MenuGroup {
    APPETIZER("애피타이저"),
    MAIN("메인"),
    DESSERT("디저트"),
    DRINK("음료");

    private final String menuGroupName;

    MenuGroup(String menuGroupName) {
        this.menuGroupName = menuGroupName;
    }

    public String getMenuGroupName() {
        return menuGroupName;
    }
}
