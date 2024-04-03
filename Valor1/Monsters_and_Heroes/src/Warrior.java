public class Warrior extends Hero{
    //private String type;
    public Warrior(String name, int level, int HP, int MP, int strength, int dexterity, int agility, int gold, int experience, Inventory inventory) {
        super(name, level, HP, MP, strength, dexterity, agility, gold, experience, inventory);
        this.setType("Warrior");
    }

    @Override
    public String toString() {
        return Formatter.space("Warrior", 12) + super.toString();
    }



}
