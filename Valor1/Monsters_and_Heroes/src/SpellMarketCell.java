import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SpellMarketCell extends MarketCell implements Formatter{
    private ArrayList<Spell> spellList;
    public SpellMarketCell(){
        spellList = new ArrayList<Spell>();
        createSpells();
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

    public void createSpells(){
        try {
            File file = new File("src/FireSpells.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str;
            br.readLine();
            for (int i = 0; i < 5; i++){
                str = br.readLine();
                if (str == null)
                    break;
                FireSpell s = createFireSpell(str);
                spellList.add(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            File file = new File("src/IceSpells.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str;
            br.readLine();
            for (int i = 0; i < 4; i++){
                str = br.readLine();
                if (str == null)
                    break;
                IceSpell s = createIceSpell(str);
                spellList.add(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            File file = new File("src/LighteningSpells.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str;
            br.readLine();
            for (int i = 0; i < 4; i++){
                str = br.readLine();
                if (str == null)
                    break;
                LighteningSpell s = createLighteningSpell(str);
                spellList.add(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FireSpell createFireSpell(String str){
        String[] vals = str.split("\\s+");
        return new FireSpell(vals[0], Integer.parseInt(vals[1]), Integer.parseInt(vals[2]),
                Integer.parseInt(vals[3]), Integer.parseInt(vals[4]));
    }

    public IceSpell createIceSpell(String str){
        String[] vals = str.split("\\s+");
        return new IceSpell(vals[0], Integer.parseInt(vals[1]), Integer.parseInt(vals[2]),
                Integer.parseInt(vals[3]), Integer.parseInt(vals[4]));
    }

    public LighteningSpell createLighteningSpell(String str){
        String[] vals = str.split("\\s+");
        return new LighteningSpell(vals[0], Integer.parseInt(vals[1]), Integer.parseInt(vals[2]),
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
                + Formatter.space("damage", 10)
                + Formatter.space("mana cost", 10) + "\n";
        int i = 1;
        for (Spell s : spellList) {
            str += Formatter.space(Integer.toString(i), 10)
                    + s.toString() + "\n";
            i++;
        }
        System.out.println(str);

    }
    @Override
    public void buyItem(Hero hero){
        System.out.println(hero.getName()+", Please enter the number of spell you want to buy:");
        Scanner sc = new Scanner(System.in);
        String temp = sc.next();
        while(temp.length()!=1 || temp.charAt(0) < 49 || temp.charAt(0) > 48 + spellList.size()){
            System.out.println("Should be between 1 and number of spells! Input Again!");
            temp = sc.next();
        }
        Spell s = spellList.get(temp.charAt(0)-49);
        buy(hero, s);

    }

    private void buy(Hero hero, Spell s){
        // Check whether there's enough money or level
        if (hero.getLevel() < s.getRequiredLevel()){
            System.out.println("You don't have enough level!");
            return;
        }
        if (hero.getGold() < s.getCost()){
            System.out.println("You don't have enough level!");
            return;
        }
        hero.getInventory().getSpellBag().addItem(s);
        hero.setGold(hero.getGold()-s.getCost());
        System.out.println("You've purchased successfully!");
    }

    @Override
    public String toString(){
        if(this.getM() == null){
            return "S  ";
        }else{
            return "SH ";
        }
    }
}

