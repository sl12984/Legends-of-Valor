public class Monster {
    private String name;
    private int level;
    private int HP;
    private int damage;
    private int defense;
    private int dodge;
    // constructor
    public Monster(String name, int level, int HP, int damage, int defense, int dodge) {
        this.name = name;
        this.level = level;
        this.HP = HP;
        this.damage = damage;
        this.defense = defense;
        this.dodge = dodge;
    }

    // Get and Set
    public String getName(){
        return name;
    }

    public int getLevel(){
        return level;
    }

    public int getHP(){
        return HP;
    }

    public int getDamage(){
        return damage;
    }

    public int getDefense(){
        return defense;
    }

    public int getDodge(){
        return dodge;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setLevel(int level){
        this.level = level;
    }

    public void setHP(int HP){
        this.HP = HP;
    }

    public void setDamage(int damage){
        this.damage = damage;
    }

    public void setDefense(int defense){
        this.defense = defense;
    }

    public void setDodge(int dodge){
        this.dodge = dodge;
    }

    @Override
    public String toString() {
        String str = Formatter.space(name, 20)
                + Formatter.space(Integer.toString(level), 10)
                + Formatter.space(Integer.toString(HP), 10)
                + Formatter.space(Integer.toString(damage), 10)
                + Formatter.space(Integer.toString(defense), 10)
                + Formatter.space(Integer.toString(dodge), 10);
        return str;
    }
}
