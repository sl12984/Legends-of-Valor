import java.util.ArrayList;

public class PotionBag {
    ArrayList<Potion> potionList;
    public PotionBag(){
        this.potionList = new ArrayList<Potion>();
    }

    public void addItem(Potion p){
        potionList.add(p);
    }


    public void removeItem(Potion p){
        potionList.remove(p);
    }

    public ArrayList<Potion> getPotionList(){
        return potionList;
    }
}
