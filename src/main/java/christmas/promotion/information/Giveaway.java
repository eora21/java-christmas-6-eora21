package christmas.promotion.information;

import christmas.information.Amount;
import christmas.information.Quantity;
import christmas.menu.Menu;

public final class Giveaway implements Beneficial {
    private final Menu menu;
    private final Quantity quantity;

    public Giveaway(Menu menu, Quantity quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    @Override
    public Benefit convertToBenefit() {
        Amount amount = menu.getAmount();
        int multiplyValue = quantity.quantity();
        return new Benefit(amount.multiplyAmount(multiplyValue));
    }

    public Menu getMenu() {
        return menu;
    }

    public Quantity getQuantity() {
        return quantity;
    }
}
