public class Exoskeleton extends Monster{
    private String type;
    // constructor
    public Exoskeleton(String name, int level, int HP, int damage, int defense, int dodge) {
        super(name, level, HP, damage, defense, dodge);
        this.type = "Exoskeleton";
    }

    // toString for Exoskeleton
    @Override
    public String toString() {
        String type = Formatter.space("Exoskeleton", 12);
        return type + super.toString();
    }
}
