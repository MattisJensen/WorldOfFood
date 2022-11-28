package layer.domain.item;

public class Item {

    private String name;
    private String description;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
