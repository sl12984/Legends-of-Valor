public class Armor extends Item{
    private int damageReduction;
    public Armor(String name, int cost, int requiredLevel, int damageReduction) {
        super(name, cost, requiredLevel);
        this.damageReduction = damageReduction;
    }

    public int getDamageReduction() {
        return damageReduction;
    }

    public void setDamageReduction(int damageReduction) {
        this.damageReduction = damageReduction;
    }

    @Override
    public String toString() {
        return super.toString()
                + Formatter.space(Integer.toString(damageReduction), 10);
    }
}
