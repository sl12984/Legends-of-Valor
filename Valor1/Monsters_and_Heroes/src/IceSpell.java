public class IceSpell extends Spell{
    public IceSpell(String name, int cost, int requiredLevel, int damage, int manaCost) {
        super(name, cost, requiredLevel, damage, manaCost);
        this.setType("Ice");
    }

    @Override
    public String toString() {
        return Formatter.space("Ice", 10)
                + super.toString();
    }
}
