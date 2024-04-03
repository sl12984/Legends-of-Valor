import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PotionMarketCell extends MarketCell implements Formatter{
    private ArrayList<Potion> potionList;
    public PotionMarketCell(){
        potionList = new ArrayList<Potion>();
        createPotions();
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

    public void createPotions(){
        try {
            File file = new File("src/Potions.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str;
            br.readLine();
            for (int i = 0; i < 6; i++){
                str = br.readLine();
                if (str == null)
                    break;
                Potion p = createPotion(str);
                potionList.add(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Potion createPotion(String str){
        String[] vals = str.split("\\s+");
        return new Potion(vals[0], Integer.parseInt(vals[1]), Integer.parseInt(vals[2]),
                Integer.parseInt(vals[3]), vals[4]);
    }
    @Override
    public void printItems(){
        System.out.println("The Items in the market:");
        String str = Formatter.space("Number", 10)
                + Formatter.space("Type", 10)
                + Formatter.space("Name", 20)
                + Formatter.space("cost", 10)
                + Formatter.space("level", 10)
                + Formatter.space("increase", 10)
                + Formatter.space("affected", 10) + "\n";
        int i = 1;
        for (Potion p : potionList) {
            str += Formatter.space(Integer.toString(i), 10)
                    + Formatter.space("Potion", 10)
                    + p.toString() + "\n";
            i++;
        }
        System.out.println(str);

    }
    @Override
    public void buyItem(Hero hero){
        System.out.println(hero.getName()+", Please enter the number of potion you want to buy:");
        Scanner sc = new Scanner(System.in);
        String temp = sc.next();
        while(temp.length()!=1 || temp.charAt(0) < 49 || temp.charAt(0) > 48 + potionList.size()){
            System.out.println("Should be between 1 and number of potions! Input Again!");
            temp = sc.next();
        }
        Potion p = potionList.get(temp.charAt(0)-49);
        buy(hero, p);

    }

    private void buy(Hero hero, Potion p){
        // Check whether there's enough money or level
        if (hero.getLevel() < p.getRequiredLevel()){
            System.out.println("You don't have enough level!");
            return;
        }
        if (hero.getGold() < p.getCost()){
            System.out.println("You don't have enough level!");
            return;
        }
        hero.getInventory().getPotionBag().addItem(p);
        hero.setGold(hero.getGold()-p.getCost());
        System.out.println("You've purchased successfully!");
    }

    @Override
    public String toString(){
        if(this.getM() == null){
            return "P  ";
        }else{
            return "PH ";
        }
    }
}

