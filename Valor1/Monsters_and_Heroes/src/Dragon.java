public class Dragon extends Monster{
    private String type;
    // constructor
    public Dragon(String name, int level, int HP, int damage, int defense, int dodge) {
        super(name, level, HP, damage, defense, dodge);
        this.type = "Dragon";
    }

    // toString for Dragon
    @Override
    public String toString() {
        String type = Formatter.space("Dragon", 12);
        return type + super.toString();
    }
}
