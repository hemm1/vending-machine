package no.bekk;

import java.util.ArrayList;
import java.util.List;

public class Money {

    private int totalMoney = 0;

    public void addQuarter() {
        totalMoney += 25;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void addNickle() {
        totalMoney += 5;
    }

    public void addDime() {
        totalMoney += 10;
    }

    public void addDollar() {
        totalMoney += 100;
    }

    public String returnCoins() {


        int moneyToReturn = totalMoney;

        List<String> output = new ArrayList<>();

        while (moneyToReturn >= 0) {
            if (moneyToReturn >= 100) {
                output.add("$");
                moneyToReturn -= 100;
            } else if (moneyToReturn >= 25) {
                output.add("Q");
                moneyToReturn -= 25;
            } else if (moneyToReturn >= 10) {
                output.add("D");
                moneyToReturn -= 10;
            } else if (moneyToReturn >= 5) {
                output.add("N");
                moneyToReturn -= 5;
            } else {
                break;
            }
        }

        totalMoney = moneyToReturn;

        return String.join(", ", output);
    }

    public void remove(int price) {
        totalMoney -= price;
    }
}
