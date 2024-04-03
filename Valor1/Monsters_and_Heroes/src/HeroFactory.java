import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class HeroFactory {
    ArrayList<Hero> heroList;
    public HeroFactory(){
        heroList = new ArrayList<Hero>();
        this.createWarriors();
        this.createSorcerers();
        this.createPaladins();
    }
    public HeroFactory getHeroFactory() {
        return new HeroFactory();
    }

    // Create a Warrior
    public void createWarriors() {
        try {
            File file = new File("src/Warriors.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str;
            br.readLine();
            for (int i = 0; i < 6; i++){
                str = br.readLine();
                if (str == null)
                    break;
                Warrior w = createWarrior(str);
                heroList.add(w);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Warrior createWarrior(String str){
        String[] vals = str.split("\\s+");
        return new Warrior(vals[0], 1, 100, Integer.parseInt(vals[1]), Integer.parseInt(vals[2]),
                Integer.parseInt(vals[3]), Integer.parseInt(vals[4]), Integer.parseInt(vals[5]),
                Integer.parseInt(vals[6]), new Inventory());
    }

    public void createSorcerers() {
        try {
            File file = new File("src/Sorcerers.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str;
            br.readLine();
            for (int i = 0; i < 6; i++){
                str = br.readLine();
                if (str == null)
                    break;
                Sorcerer s = createSorcerer(str);
                heroList.add(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Sorcerer createSorcerer(String str){
        String[] vals = str.split("\\s+");
        return new Sorcerer(vals[0], 1, 100, Integer.parseInt(vals[1]), Integer.parseInt(vals[2]),
                Integer.parseInt(vals[3]), Integer.parseInt(vals[4]), Integer.parseInt(vals[5]),
                Integer.parseInt(vals[6]), new Inventory());
    }

    public void createPaladins() {
        try {
            File file = new File("src/Paladins.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str;
            br.readLine();
            for (int i = 0; i < 6; i++){
                str = br.readLine();
                if (str == null)
                    break;
                Paladin p = createPaladin(str);
                heroList.add(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Paladin createPaladin(String str){
        String[] vals = str.split("\\s+");
        return new Paladin(vals[0], 1, 100, Integer.parseInt(vals[1]), Integer.parseInt(vals[2]),
                Integer.parseInt(vals[3]), Integer.parseInt(vals[4]), Integer.parseInt(vals[5]),
                Integer.parseInt(vals[6]), new Inventory());
    }

    // Randomly choose num heroes
    public ArrayList<Hero> getHeroes(int num) {
        ArrayList<Hero> ans = new ArrayList<Hero>();
        Collections.shuffle(heroList);
        for(int i = 0; i < num; i++) {
            ans.add(heroList.get(i));
        }
        return ans;
    }

    public ArrayList<Hero> AllHeroes(){
        return this.heroList;
    }


}
