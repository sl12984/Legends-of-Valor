import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ArmorMarketCell extends MarketCell implements Formatter{
    private ArrayList<Armor> armorList;
    public ArmorMarketCell(){
        armorList = new ArrayList<Armor>();
        createArmors();
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

    public void createArmors(){
        try {
            File file = new File("src/Armory.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str;
            br.readLine();
            for (int i = 0; i < 6; i++){
                str = br.readLine();
                if (str == null)
                    break;
                Armor a = createArmor(str);
                armorList.add(a);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Armor createArmor(String str){
        String[] vals = str.split("\\s+");
        return new Armor(vals[0], Integer.parseInt(vals[1]), Integer.parseInt(vals[2]),
                Integer.parseInt(vals[3]));
    }
    @Override
    public void printItems(){
        System.out.println("The Items in the market:");
        String str = Formatter.space("Number", 10)
                + Formatter.space("Type", 10)
                + Formatter.space("Name", 20)
                + Formatter.space("cost", 10)
                + Formatter.space("level", 10)
                + Formatter.space("damage reduction", 10) + "\n";
        int i = 1;
        for (Armor a : armorList) {
            str += Formatter.space(Integer.toString(i), 10)
                    + Formatter.space("Armor", 10)
                    + a.toString() + "\n";
            i++;
        }
        System.out.println(str);

    }
    @Override
    public void buyItem(Hero hero){
        System.out.println(hero.getName()+", Please enter the number of armor you want to buy:");
        Scanner sc = new Scanner(System.in);
        String temp = sc.next();
        while(temp.length()!=1 || temp.charAt(0) < 49 || temp.charAt(0) > 48 + armorList.size()){
            System.out.println("Should be between 1 and number of armors! Input Again!");
            temp = sc.next();
        }
        Armor a = armorList.get(temp.charAt(0)-49);
        buy(hero, a);

    }

    private void buy(Hero hero, Armor a){
        // Check whether there's enough money or level
        if (hero.getLevel() < a.getRequiredLevel()){
            System.out.println("You don't have enough level!");
            return;
        }
        if (hero.getGold() < a.getCost()){
            System.out.println("You don't have enough level!");
            return;
        }
        hero.getInventory().getArmorBag().addItem(a);
        hero.setGold(hero.getGold()-a.getCost());
        System.out.println("You've purchased successfully!");
    }

    @Override
    public String toString(){
        if(this.getM() == null){
            return "A  ";
        }else{
            return "AH ";
        }
    }
}

