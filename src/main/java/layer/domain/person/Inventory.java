package layer.domain.person;

import layer.interfaces.GameSettings;
import layer.domain.item.Food;
import layer.domain.item.Item;

import java.util.ArrayList;

public class Inventory implements GameSettings {

    private final ArrayList<Item> items = new ArrayList<Item>();

    /* add og remove item fra inventaret */
    public void addFoodItem(Food foodItem, int amount) {
        for (int i = 0; i < amount; i++) {
            items.add(new Food(foodItem.getName(), foodItem.getFoodPoints(), foodItem.getClimatePoints()));
        }
    }

    public boolean enoughInvSpace(int amount) {
        return items.size() + amount <= INVENTORY_SIZE;
    }

    public void removeItem(Item item) {
        for (Item i : items) { // sådan sikrer vi at der fjernes et item med samme navn, selv om selve objektet ikke er den samme
            if (i.getName().equalsIgnoreCase(item.getName())) {
                items.remove(i);
                return;
            }
        }
    }

    /* retunering af inventar som liste eller String */
    public ArrayList<Item> getItems() {
        return items;
    }
}
