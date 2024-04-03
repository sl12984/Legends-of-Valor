import java.util.ArrayList;

public class MonsterParty {
    private ArrayList<Monster> monsterList;
    // Constructor
    public MonsterParty(int num,int level) {
        monsterList = new MonsterFactory().getMonsters(num,level);
    }
    public MonsterParty() {
        monsterList = new MonsterFactory().allMonsters();
    }

    public ArrayList<Monster> getMonsterParty() {
        return monsterList;
    }

    public int size(){
        return monsterList.size();
    }

    @Override
    public String toString() {
        String str = "----------------------------------------------------------------------------------------------------------------\n";
        str += Formatter.space("Type", 12)
                + Formatter.space("Name", 20)
                + Formatter.space("Level", 10)
                + Formatter.space("HP", 10)
                + Formatter.space("Damage", 10)
                + Formatter.space("Defense", 10)
                + Formatter.space("Dodge", 10)
                + "\n";
        for (Monster monster: this.getMonsterParty()) {
            str += monster.toString() + "\n";
        }
        return str;
    }
}
