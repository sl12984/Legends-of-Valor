import java.util.ArrayList;

public class SpellBag {
    ArrayList<Spell> spellList;
    public SpellBag(){
        spellList = new ArrayList<Spell>();
    }


    public void addItem(Spell s){
        spellList.add(s);
    }


    public void removeItem(Spell s){
        spellList.remove(s);
    }

    public ArrayList<Spell> getSpellList(){
        return spellList;
    }
}
