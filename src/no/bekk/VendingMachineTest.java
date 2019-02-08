package no.bekk;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VendingMachineTest {

    private VendingMachine vendingMachine;

    @BeforeEach
    void setUp() {
        vendingMachine = new VendingMachine(new Inventory(1, 0, 2));
    }

    @Test
    void handlesOneQuarter() {
        vendingMachine.handle("Q");

        assertEquals(25, vendingMachine.getAmountOfMoney());
    }

    @Test
    void handlesTwoQuarters() {
        vendingMachine.handle("Q, Q");

        assertEquals(50, vendingMachine.getAmountOfMoney());
    }

    @Test
    void handlesThreeQuarters() {
        vendingMachine.handle("Q, Q, Q");

        assertEquals(75, vendingMachine.getAmountOfMoney());
    }

    @Test
    void handlesOneNickle() {
        vendingMachine.handle("N");

        assertEquals(5, vendingMachine.getAmountOfMoney());
    }


    @Test
    void handlesMoreMoney() {
        vendingMachine.handle("$, D, N, Q");

        assertEquals(140, vendingMachine.getAmountOfMoney());
    }

    @Test
    void returnsSingleCoin() {
        String output = vendingMachine.handle("N, COIN-RETURN");

        assertEquals("N", output);
    }

    @Test
    void returnsOtherCoin() {
        String output = vendingMachine.handle("N, $, COIN-RETURN");

        assertEquals("$, N", output);
    }

    @Test
    void returnsTwoNickles() {
        String output = vendingMachine.handle("N, N, COIN-RETURN");

        assertEquals("D", output);
    }


    @Test
    void returnsBunchOfCoins() {
        String output = vendingMachine.handle("N, $, D, D, Q, N, Q, Q, COIN-RETURN");

        assertEquals("$, $, N", output);
    }

    @Test
    void vendsA() {
        String output = vendingMachine.handle("Q, Q, D, N, GET-A");

        assertEquals("A", output);
    }

    @Test
    void cantAffordA() {
        String output = vendingMachine.handle("Q, GET-A");

        assertEquals("NOT ENOUGH MONEY", output);
        assertEquals(25, vendingMachine.getAmountOfMoney());
    }

    @Test
    void cantSellIfNotInStock() {
        String output = vendingMachine.handle("$, $, GET-B");

        assertEquals("NOT IN STOCK", output);
        assertEquals(200, vendingMachine.getAmountOfMoney());
    }

    @Test
    void decrementsStockWhenBuyingItem() {
        String output = vendingMachine.handle("Q, Q, D, N, GET-A");
        assertEquals("A", output);

        String nextOutput = vendingMachine.handle("Q, Q, D, N, GET-A");
        assertEquals("NOT IN STOCK", nextOutput);
    }

    @Test
    void hasCorrectPriceForC() {
        String output = vendingMachine.handle("Q, Q, $, GET-C");

        assertEquals("C", output);
    }

    @Test
    void givesChange() {
        String output = vendingMachine.handle("$, $, GET-C");

        assertEquals("C, Q, Q", output);
    }


    @Test
    void testCasesFromSpec() {
        vendingMachine = new VendingMachine(new Inventory(1, 1, 2));
        String output = vendingMachine.handle("Q, Q, Q, Q, GET-B");

        assertEquals("B", output);
        vendingMachine = new VendingMachine(new Inventory(1, 1, 2));
        output = vendingMachine.handle("Q, Q, COIN-RETURN");

        assertEquals("Q, Q", output);


        output = vendingMachine.handle("$, GET-A");

        assertEquals("A, Q, D", output);
    }

}