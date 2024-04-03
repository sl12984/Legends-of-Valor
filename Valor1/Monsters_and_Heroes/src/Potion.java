public class Potion extends Item {
    // The increase of those attributes
    private int HP;
    private int MP;
    private int strength;
    private int dexterity;
    private int agility;

    // Constructor
    public Potion(String name, int cost, int requiredLevel, int attributeIncrease, String attributeAffected){
        super(name, cost, requiredLevel);
        // Read the String of attributeAffected
        int flag = 0;
        int temp = 0;
        String s = attributeAffected;
        while(temp < s.length()+1){
            if(temp < s.length() && s.charAt(temp)!='/'){
                temp++;
            }else{
                String attr = s.substring(flag,temp);
                if(attr.equals("Health")){
                    this.HP = attributeIncrease;
                }
                if(attr.equals("Mana")){
                    this.MP = attributeIncrease;
                }
                if(attr.equals("Strength")){
                    this.strength = attributeIncrease;
                }
                if(attr.equals("Agility")){
                    this.agility = attributeIncrease;
                }
                if(attr.equals("Dexterity")){
                    this.dexterity = attributeIncrease;
                }
                flag = temp + 1;
                temp++;

            }
        }
    }

    // Get and Set
    public int getHP() {
        return HP;
    }

    public int getMP() {
        return MP;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getAgility() {
        return agility;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setMP(int MP) {
        this.MP = MP;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }



    @Override
    public String toString(){
        return super.toString()
                + Formatter.space(Integer.toString(HP), 10)
                + Formatter.space(Integer.toString(MP), 10)
                + Formatter.space(Integer.toString(strength), 10)
                + Formatter.space(Integer.toString(dexterity), 10)
                + Formatter.space(Integer.toString(agility), 10);
    }

}
