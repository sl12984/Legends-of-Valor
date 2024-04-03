import java.util.ArrayList;

public class HeroParty {
    private ArrayList<Hero> heroList;
    // Constructor
    public HeroParty(int num) {
        heroList = new HeroFactory().getHeroes(num);
    }
    public HeroParty() {
        heroList = new HeroFactory().AllHeroes();
    }

    public ArrayList<Hero> getHeroParty() {
        return heroList;
    }
    public int size(){
        return heroList.size();
    }
    @Override
    public String toString() {
        String str = "----------------------------------------------------------------------------------------------------------------\n";
        str += Formatter.space("Number", 10)
                + Formatter.space("Type", 12)
                + Formatter.space("Name", 20)
                + Formatter.space("Level", 10)
                + Formatter.space("HP", 10)
                + Formatter.space("MP", 10)
                + Formatter.space("Strength", 10)
                + Formatter.space("Dexterity", 10)
                + Formatter.space("Agility", 10)
                + Formatter.space("Gold", 10)
                + Formatter.space("Experience", 10) + "\n";

        int num = 1;
        for (Hero hero: this.getHeroParty()) {
            str += Formatter.space(Integer.toString(num), 10) + hero.toString() + "\n";
            num++;
        }
        return str;
    }

}
