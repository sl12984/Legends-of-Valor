public class Paladin extends Hero{
    //private String type;
    public Paladin(String name, int level, int HP, int MP, int strength, int dexterity, int agility, int gold, int experience, Inventory inventory) {
        super(name, level, HP, MP, strength, dexterity, agility, gold, experience, inventory);
        this.setType("Warrior");
    }

    @Override
    public String toString() {
        return Formatter.space("Paladin", 12) + super.toString();
    }

}
