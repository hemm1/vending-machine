package no.bekk;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private List<Item> items;

    public Inventory(int amountOfA, int amountOfB, int amountOfC) {
        Item A = new Item(ItemType.A, amountOfA);
        Item B = new Item(ItemType.B, amountOfB);
        Item C = new Item(ItemType.C, amountOfC);

        items = new ArrayList<>();
        items.add(A);
        items.add(B);
        items.add(C);
    }


    public void buy(ItemType itemType) {
        getItemFromType(itemType).count -= 1;
    }

    public boolean hasInStock(ItemType itemType) {
        return getItemFromType(itemType).count > 0;
    }

    private Item getItemFromType(ItemType itemType) {
        return items.stream().filter(item -> item.selector == itemType).findAny().get();
    }

    private class Item {
        private ItemType selector;
        private int count;

        private Item(ItemType selector, int count) {
            this.selector = selector;
            this.count = count;
        }
    }

    public enum ItemType {
        A(65), B(100), C(150);

        public final int price;

        ItemType(int price) {
            this.price = price;
        }

        public int getPrice() {
            return price;
        }
    }

}





