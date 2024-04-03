import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class WeaponMarketCell extends MarketCell implements Formatter{
    private ArrayList<Weapon> weaponList;
    public WeaponMarketCell(){
        weaponList = new ArrayList<Weapon>();
        createWeapons();
    }


    @Override
    public void heroCome(HeroParty h) {
        super.heroCome(h);
        System.out.println("Welcome to the market, you may enter M to see items or enter others to continue");
        Scanner sc = new Scanner(System.in);
        String temp = sc.next();
        if (temp.length()==1 && (temp.charAt(0)=='M' || temp.charAt(0)=='m')) {
            printItems();
            System.out.println("Do you want to purchase any item? (y/n)");
            String temp2 = sc.next();
            if (temp2.length()==1 && (temp2.charAt(0)=='Y' || temp2.charAt(0)=='y')) {
                for(Hero hero: h.getHeroParty()){
                    buyItem(hero);
                }
            }
        }
    }

    public void createWeapons(){
        try {
            File file = new File("src/Weaponry.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str;
            br.readLine();
            for (int i = 0; i < 6; i++){
                str = br.readLine();
                if (str == null)
                    break;
                Weapon w = createWeapon(str);
                weaponList.add(w);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Weapon createWeapon(String str){
        String[] vals = str.split("\\s+");
        return new Weapon(vals[0], Integer.parseInt(vals[1]), Integer.parseInt(vals[2]),
                Integer.parseInt(vals[3]), Integer.parseInt(vals[4]));
    }
    @Override
    public void printItems(){
        System.out.println("The Items in the market:");
        String str = Formatter.space("Number", 10)
                + Formatter.space("Type", 10)
                + Formatter.space("Name", 20)
                + Formatter.space("cost", 10)
                + Formatter.space("level", 10)
                + Formatter.space("required hands", 10) + "\n";
        int i = 1;
        for (Weapon a : weaponList) {
            str += Formatter.space(Integer.toString(i), 10)
                    + Formatter.space("Weapon", 10)
                    + a.toString() + "\n";
            i++;
        }
        System.out.println(str);

    }
    @Override
    public void buyItem(Hero hero){
        System.out.println(hero.getName()+", Please enter the number of weapon you want to buy:");
        Scanner sc = new Scanner(System.in);
        String temp = sc.next();
        while(temp.length()!=1 || temp.charAt(0) < 49 || temp.charAt(0) > 48 + weaponList.size()){
            System.out.println("Should be between 1 and number of weapons! Input Again!");
            temp = sc.next();
        }
        Weapon w = weaponList.get(temp.charAt(0)-49);
        buy(hero, w);

    }

    private void buy(Hero hero, Weapon w){
        // Check whether there's enough money or level
        if (hero.getLevel() < w.getRequiredLevel()){
            System.out.println("You don't have enough level!");
            return;
        }
        if (hero.getGold() < w.getCost()){
            System.out.println("You don't have enough level!");
            return;
        }
        hero.getInventory().getWeaponBag().addItem(w);
        hero.setGold(hero.getGold()-w.getCost());
        System.out.println("You've purchased successfully!");
    }

    @Override
    public String toString(){
        if(this.getM() == null){
            return "W  ";
        }else{
            return "WH ";
        }
    }
}
