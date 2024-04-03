public class LighteningSpell extends Spell{
    public LighteningSpell(String name, int cost, int requiredLevel, int damage, int manaCost) {
        super(name, cost, requiredLevel, damage, manaCost);
        this.setType("Lightening");
    }

    @Override
    public String toString() {
        return Formatter.space("Lightening", 10)
                + super.toString();
    }
}
