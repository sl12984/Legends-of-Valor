import java.util.Scanner;

public class Hero {
    private String name;
    private String type;
    private int level;
    private int HP;
    private int MP;
    private int strength;
    private int dexterity;
    private int agility;
    private int gold;
    private int experience;
    private Inventory inventory;
    // Equipped
    private Weapon weapon;
    private Armor armor;

    // constructor
    public Hero(String name, int level, int HP, int MP, int strength,
                int dexterity, int agility, int gold, int experience, Inventory inventory) {
        this.name = name;
        this.type = null;
        this.level = level;
        this.HP = HP;
        this.MP = MP;
        this.strength = strength;
        this.dexterity = dexterity;
        this.agility = agility;
        this.gold = gold;
        this.experience = experience;
        this.inventory = inventory;
        this.weapon = null;
        this.armor = null;
    }

    @Override
    public String toString() {
        String str = Formatter.space(name, 20)
                + Formatter.space(Integer.toString(level), 10)
                + Formatter.space(Integer.toString(HP), 10)
                + Formatter.space(Integer.toString(MP), 10)
                + Formatter.space(Integer.toString(strength), 10)
                + Formatter.space(Integer.toString(dexterity), 10)
                + Formatter.space(Integer.toString(agility), 10)
                + Formatter.space(Integer.toString(gold), 10)
                + Formatter.space(Integer.toString(experience), 10);
        return str;
    }

    // getters ans setters
    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setLevel(int level) {
        this.level = level;
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

    public void setGold(int gold) {
        this.gold = gold;
    }


    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setWeapon(Weapon w){
        this.weapon = w;
    }

    public void setArmor(Armor a){
        this.armor = a;
    }

    public String getName() {
        return name;
    }

    public String getType(){
        return type;
    }

    public int getLevel() {
        return level;
    }

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

    public int getGold() {
        return gold;
    }

    public int getExperience() {
        return experience;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Weapon getWeapon(){
        return weapon;
    }

    public Armor getArmor(){
        return armor;
    }

    public void drinkPotion(){
        System.out.println("The Potions in your bags are:");
        for(int i=0;i<this.getInventory().getPotionBag().getPotionList().size();i++){
            System.out.print((i+1) + ": " + this.getInventory().getPotionBag().getPotionList().get(i).getName()+" ");
        }
        System.out.println("");
        System.out.println("Please input the potion you want:(0 means Finish)");
        Scanner sc = new Scanner(System.in);
        String temp = sc.next();
        // Assume potions less than 10
        while(temp.length()!=1 || temp.charAt(0)<48 || temp.charAt(0)>48+this.getInventory().getPotionBag().getPotionList().size()){
            System.out.println("Invalid number! Please Input Again");
            temp = sc.next();
        }
        if(temp.charAt(0) == 48){
            return;
        }else{
            this.drink(this.getInventory().getPotionBag().getPotionList().get(temp.charAt(0)-49));
        }
    }

    public void drink(Potion p){
        this.setHP(Math.min(this.getLevel()*100, this.getHP()+p.getHP()));
        this.setMP(this.getMP()+p.getMP());
        this.setAgility(this.getAgility()+p.getAgility());
        this.setDexterity(this.getDexterity()+p.getDexterity());
        this.setStrength(this.getStrength()+p.getStrength());
        System.out.print(this.name + " drinks "+p.getName()+" and get ");
        System.out.print(p.getHP()+" HP, "+p.getMP()+" MP, "+p.getStrength()+" Strength, ");
        System.out.println(p.getDexterity()+" Dexterity, "+p.getAgility()+" Agility!");
    }

    public void equipWeapon(){
        System.out.println("The Weapons in your bags are:");
        for(int i=0;i<this.getInventory().getWeaponBag().getWeaponList().size();i++){
            System.out.print((i+1) + ": " + this.getInventory().getWeaponBag().getWeaponList().get(i).getName()+" ");
        }
        System.out.println("");
        System.out.println("Please input the weapon you want:(0 means Finish)");
        Scanner sc = new Scanner(System.in);
        String temp = sc.next();
        // Assume weapons less than 10
        while(temp.length()!=1 || temp.charAt(0)<48 || temp.charAt(0)>48+this.getInventory().getWeaponBag().getWeaponList().size()){
            System.out.println("Invalid number! Please Input Again");
            temp = sc.next();
        }
        if(temp.charAt(0) == 48){
            return;
        }else{
            this.equipw(temp.charAt(0)-49);
        }
    }

    public void equipw(int i){
        Weapon w = this.getInventory().getWeaponBag().getWeaponList().get(i);
        if(this.getWeapon() == null){
            this.setWeapon(w);
            this.getInventory().getWeaponBag().removeItem(w);
        }else{
            Weapon w2 = this.getWeapon();
            this.setWeapon(w);
            this.getInventory().getWeaponBag().removeItem(w);
            this.getInventory().getWeaponBag().addItem(w2);
        }
        System.out.println("Weapon Successfully Equipped!");
    }

    public void equipArmor(){
        System.out.println("The Armors in your bags are:");
        for(int i=0;i<this.getInventory().getArmorBag().getArmorList().size();i++){
            System.out.print((i+1) + ": " + this.getInventory().getArmorBag().getArmorList().get(i).getName()+" ");
        }
        System.out.println("");
        System.out.println("Please input the armor you want:(0 means Finish)");
        Scanner sc = new Scanner(System.in);
        String temp = sc.next();
        // Assume armors less than 10
        while(temp.length()!=1 || temp.charAt(0)<48 || temp.charAt(0)>48+this.getInventory().getArmorBag().getArmorList().size()){
            System.out.println("Invalid number! Please Input Again");
            temp = sc.next();
        }
        if(temp.charAt(0) == 48){
            return;
        }else{
            this.equipa(temp.charAt(0)-49);
        }
    }

    public void equipa(int i){
        Armor a = this.getInventory().getArmorBag().getArmorList().get(i);
        if(this.getArmor() == null){
            this.setArmor(a);
            this.getInventory().getArmorBag().removeItem(a);
        }else{
            Armor a2 = this.getArmor();
            this.setArmor(a);
            this.getInventory().getArmorBag().removeItem(a);
            this.getInventory().getArmorBag().addItem(a2);
        }
        System.out.println("Armor Successfully Equipped!");
    }

    // Check the levelup process after gaining experience
    public void gainExp(int expgain){
        int temp = expgain;
        int flag = 0;
        while(this.experience+temp >= this.level*10){
            flag = 1;
            temp-=this.level*10-this.experience;
            this.level++;
            this.HP = this.level * 100;
            this.experience = 0;
            this.MP = this.MP * 11 / 10;
            if(this.type.equals("Warrior")){
                this.strength = this.strength * 11 / 10;
                this.agility = this.agility * 11 / 10;
                this.dexterity = this.dexterity * 21 / 20;
            }else if(this.type.equals("Sorcerer")){
                this.strength = this.strength * 21 / 20;
                this.agility = this.agility * 11 / 10;
                this.dexterity = this.dexterity * 11 / 10;
            }else if(this.type.equals("Paladin")){
                this.strength = this.strength * 11 / 10;
                this.agility = this.agility * 21 / 20;
                this.dexterity = this.dexterity * 11 / 10;
            }

        }
        this.experience += temp;
        if(flag == 1){
            System.out.println(this.name + "Level Up!");

        }

    }
}