import java.util.ArrayList;

public class ArmorBag{
    ArrayList<Armor> armorList;
    public ArmorBag(){
        this.armorList = new ArrayList();
    }

    public void addItem(Armor a){
        armorList.add(a);
    }


    public void removeItem(Armor a){
        armorList.remove(a);
    }

    public ArrayList<Armor> getArmorList(){
        return armorList;
    }
}
