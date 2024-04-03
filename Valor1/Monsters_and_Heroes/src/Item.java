public class Item implements Formatter{
    private String name;
    private int cost;
    private int requiredLevel;

    public Item(String name, int cost, int requiredLevel) {
        this.name = name;
        this.cost = cost;
        this.requiredLevel = requiredLevel;
    }

    // Set and Get
    public void setName(String name) {
        this.name = name;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setRequiredLevel(int requiredLevel) {
        this.requiredLevel = requiredLevel;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getRequiredLevel() {
        return requiredLevel;
    }

    @Override
    public String toString() {
        String str = Formatter.space(name, 20)
                + Formatter.space(Integer.toString(cost), 10)
                + Formatter.space(Integer.toString(requiredLevel), 10);
        return str;
    }
}