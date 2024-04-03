public class FireSpell extends Spell{
    public FireSpell(String name, int cost, int requiredLevel, int damage, int manaCost) {
        super(name, cost, requiredLevel, damage, manaCost);
        this.setType("Fire");
    }

    @Override
    public String toString() {
        return Formatter.space("Fire", 10)
                + super.toString();
    }
}
