public class Spell extends Item {
    private int damage;
    private String type;
    private int mpCost;
    public Spell(String name, int cost, int requiredLevel, int damage, int mpCost) {
        super(name, cost, requiredLevel);
        this.type = null;
        this.damage = damage;
        this.mpCost = mpCost;
    }

    // Get and Set
    public int getDamage(){
        return damage;
    }

    public int getMpCost(){
        return mpCost;
    }

    public String getType(){
        return type;
    }

    public void setDamage(int damage){
        this.damage = damage;
    }

    public void setMpCost(int mpCost){
        this.mpCost = mpCost;
    }

    public void setType(String type){
        this.type = type;
    }

    @Override
    public String toString(){
        return super.toString()
                + Formatter.space(Integer.toString(damage), 10)
                + Formatter.space(Integer.toString(mpCost), 10);
    }

}
