import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class MonsterFactory {
    ArrayList<Monster> monsterList;
    public MonsterFactory(){
        monsterList = new ArrayList<Monster>();
        this.createDragons();
        this.createExoskeletons();
        this.createSpirits();
    }
    public MonsterFactory getMonsterFactory() {
        return new MonsterFactory();
    }

    // Create a Dragon
    public void createDragons() {
        try {
            File file = new File("src/Dragons.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str;
            br.readLine();
            for (int i = 0; i < 12; i++){
                str = br.readLine();
                if (str == null)
                    break;
                Dragon d = createDragon(str);
                monsterList.add(d);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Dragon createDragon(String str){
        String[] vals = str.split("\\s+");
        return new Dragon(vals[0], Integer.parseInt(vals[1]), Integer.parseInt(vals[1]) * 10, Integer.parseInt(vals[2]),
                Integer.parseInt(vals[3]), Integer.parseInt(vals[4]));
    }

    public void createExoskeletons() {
        try {
            File file = new File("src/Exoskeletons.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str;
            br.readLine();
            for (int i = 0; i < 12; i++){
                str = br.readLine();
                if (str == null)
                    break;
                Exoskeleton m = createExoskeleton(str);
                monsterList.add(m);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Exoskeleton createExoskeleton(String str){
        String[] vals = str.split("\\s+");
        return new Exoskeleton(vals[0], Integer.parseInt(vals[1]), Integer.parseInt(vals[1]) * 10, Integer.parseInt(vals[2]),
                Integer.parseInt(vals[3]), Integer.parseInt(vals[4]));
    }

    public void createSpirits() {
        try {
            File file = new File("src/Spirits.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str;
            br.readLine();
            for (int i = 0; i < 11; i++){
                str = br.readLine();
                if (str == null)
                    break;
                Spirit m = createSpirit(str);
                monsterList.add(m);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Spirit createSpirit(String str){
        String[] vals = str.split("\\s+");
        return new Spirit(vals[0], Integer.parseInt(vals[1]), Integer.parseInt(vals[1]) * 10, Integer.parseInt(vals[2]),
                Integer.parseInt(vals[3]), Integer.parseInt(vals[4]));
    }

    // Randomly choose num heroes
    public ArrayList<Monster> getMonsters(int num, int level) {
        ArrayList<Monster> tempList = new ArrayList<Monster>();
        for(Monster m: monsterList){
            if(m.getLevel()==level){
                tempList.add(m);
            }
        }

        ArrayList<Monster> ans = new ArrayList<Monster>();
        Collections.shuffle(tempList);
        for(int i = 0; i < num; i++) {
            ans.add(monsterList.get(i));
        }
        return ans;
    }

    public ArrayList<Monster> allMonsters(){
        return this.monsterList;
    }

}
