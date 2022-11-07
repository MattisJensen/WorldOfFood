package worldOfFood.implementation;

import java.util.ArrayList;

public class Inventory implements GameSettings {

    private ArrayList<Item> items = new ArrayList<Item>();

    /* add og remove item fra inventaret */
    public void addItem(Item item) {
        if (items.size() <= INVENTORY_SIZE) {
            items.add(item);
        } else {
            System.out.println("Your inventory is already full. It has room for " + INVENTORY_SIZE + " items.");
        }
    }

    public void removeItem(Item item) {
        for (Item i : items) { // sådan sikrer vi at der fjernes et item med samme navn, selv om selve objektet ikke er den samme
            if (i.getName().equalsIgnoreCase(item.getName())) {
                removeItem(i);
                return; // "return" i stedet for "break", da return 'stopper' metoden med det samme
            }
        }
    }


    /* retunering af inventar som liste eller String */
    public ArrayList<Item> getItems() {
        return items;
    }

    public void printInventory() {
        System.out.print("Your inventory contains the following items: ");
        for (Item i : items) {
            System.out.print(i.getName()+", ");
        }
        System.out.println();
    }
}
