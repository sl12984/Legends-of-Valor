public class Sorcerer extends Hero{
    //private String type;
    public Sorcerer(String name, int level, int HP, int MP, int strength, int dexterity, int agility, int gold, int experience, Inventory inventory) {
        super(name, level, HP, MP, strength, dexterity, agility, gold, experience, inventory);
        this.setType("Warrior");
    }

    @Override
    public String toString() {
        return Formatter.space("Sorcerer", 12) + super.toString();
    }

}
