package no.bekk;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static no.bekk.Inventory.ItemType.*;

public class VendingMachine {

    private final Money money;
    private final Inventory inventory;

    public VendingMachine(Inventory inventory) {
        money = new Money();
        this.inventory = inventory;
    }

    public String handle(String commands) {

        return Stream.of(commands.split(", "))
                .map(this::handleCommand)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.joining(" "));
    }

    private Optional<String> handleCommand(String c) {
        switch (c) {
            case "Q":
                money.addQuarter();
                break;
            case "N":
                money.addNickle();
                break;
            case "$":
                money.addDollar();
                break;
            case "D":
                money.addDime();
                break;
            case "GET-A":
                return of(tryToBuy(A));
            case "GET-B":
                return of(tryToBuy(B));
            case "GET-C":
                return of(tryToBuy(C));
            case "COIN-RETURN":
                return of(money.returnCoins());
            default:
                break;
        }
        return empty();
    }

    private String tryToBuy(Inventory.ItemType itemType) {
        if (money.getTotalMoney() < itemType.price) {
            return "NOT ENOUGH MONEY";
        } else if (inventory.hasInStock(itemType)) {
            inventory.buy(itemType);
            money.remove(itemType.getPrice());

            if (getAmountOfMoney() > 0) {
                return itemType.toString() + ", " + money.returnCoins();
            } else {
                return itemType.toString();
            }
        } else {
            return "NOT IN STOCK";
        }
    }

    double getAmountOfMoney() {
        return money.getTotalMoney();
    }
}
