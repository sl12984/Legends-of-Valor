import java.util.ArrayList;
public class Inventory {
    private WeaponBag weaponBag;
    private ArmorBag armorBag;
    private PotionBag potionBag;
    private SpellBag spellBag;
    // Constructor
    public Inventory(){
        weaponBag = new WeaponBag();
        armorBag = new ArmorBag();
        potionBag = new PotionBag();
        spellBag = new SpellBag();
    }

    // Add item to Inventory
    public void addWeapon(Weapon w){
        weaponBag.addItem(w);
    }

    public void addArmor(Armor a){
        armorBag.addItem(a);
    }

    public void addPotion(Potion p){
        potionBag.addItem(p);
    }

    public void addSpell(Spell s){
        spellBag.addItem(s);
    }

    public WeaponBag getWeaponBag(){
        return weaponBag;
    }

    public ArmorBag getArmorBag(){
        return armorBag;
    }

    public PotionBag getPotionBag(){
        return potionBag;
    }

    public SpellBag getSpellBag(){
        return spellBag;
    }
}
