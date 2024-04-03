public class Spirit extends Monster{
    private String type;
    // constructor
    public Spirit(String name, int level, int HP, int damage, int defense, int dodge) {
        super(name, level, HP, damage, defense, dodge);
        this.type = "Spirit";
    }

    // toString for Spirit
    @Override
    public String toString() {
        String type = Formatter.space("Spirit", 12);
        return type + super.toString();
    }
}
