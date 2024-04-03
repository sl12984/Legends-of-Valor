import java.util.ArrayList;

public class WeaponBag{
    ArrayList<Weapon> weaponList;
    public WeaponBag(){
        weaponList = new ArrayList<Weapon>();
    }


    public void addItem(Weapon w){
        weaponList.add(w);
    }


    public void removeItem(Weapon w){
        weaponList.remove(w);
    }

    public ArrayList<Weapon> getWeaponList(){
        return weaponList;
    }
}
