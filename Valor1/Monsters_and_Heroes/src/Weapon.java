public class Weapon extends Item {
    private int damage;
    private int requiredHands;
    public Weapon(String name, int cost, int requiredLevel, int damage, int requiredHands) {
        super(name, cost, requiredLevel);
        this.damage = damage;
        this.requiredHands = requiredHands;
    }

    // Get and Set
    public int getDamage(){
        return damage;
    }

    public void setDamage(int damage){
        this.damage = damage;
    }
    public int getRequiredHands() {
        return requiredHands;
    }

    public void setRequiredHands(int requiredHands) {
        this.requiredHands = requiredHands;
    }

    @Override
    public String toString() {
        return super.toString()
                + Formatter.space(Integer.toString(damage), 10)
                + Formatter.space(Integer.toString(requiredHands), 10);
    }
}
