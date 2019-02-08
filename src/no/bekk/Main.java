package no.bekk;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        VendingMachine vendingMachine = new VendingMachine(new Inventory(1, 1, 0));


        while (true) {
            System.out.println("Enter command:");
            String command = scanner.nextLine();

            System.out.println(vendingMachine.handle(command));
        }

    }
}
