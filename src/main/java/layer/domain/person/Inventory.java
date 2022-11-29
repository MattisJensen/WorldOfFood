package layer.domain.person;

import layer.domain.game.GameSettings;
import layer.domain.item.Food;
import layer.domain.item.Item;

import java.util.ArrayList;

public class Inventory implements GameSettings {

    private ArrayList<Item> items = new ArrayList<Item>();

    /* add og remove item fra inventaret */
    public void addFoodItem(Food foodItem, int amount) {
        if (items.size() + amount <= INVENTORY_SIZE) {
            for (int i = 0; i < amount; i++) {
                items.add(new Food(foodItem.getName(), foodItem.getFoodPoints(), foodItem.getClimatePoints()));
            }
        } else {
            System.out.println("Your inventory is to small. It has room for " + INVENTORY_SIZE + " items, but you are trying to add " + amount + " items.");
        }
    }

    public void removeItem(Item item) {
        for (Item i : items) { // sådan sikrer vi at der fjernes et item med samme navn, selv om selve objektet ikke er den samme
            if (i.getName().equalsIgnoreCase(item.getName())) {
                items.remove(i);
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
            System.out.print(i.getName() + ", ");
        }
        System.out.println();
    }
}
