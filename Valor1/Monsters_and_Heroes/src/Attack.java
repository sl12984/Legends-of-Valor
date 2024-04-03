import java.util.Random;
import java.util.Scanner;

public class Attack {
    public static void attack_1(Hero h, Monster m){
        System.out.println(h.getName() + " attacks " + m.getName());
        int dodgeChance = m.getDodge();
        Random rand = new Random();
        int num = rand.nextInt(101);
        // monster dodged
        if (num < dodgeChance) {
            System.out.println(m.getName() + " successfully dodged");
            return;
        }
        int weaponDamage = 0;
        if(h.getWeapon() != null){
            weaponDamage = h.getWeapon().getDamage();
        }
        int damage = Math.max(0, (h.getStrength()+weaponDamage-m.getDefense())/20);
        m.setHP(Math.max(0,m.getHP()-damage));
        System.out.println("The damage is "+damage);
        System.out.println(m.getName()+" has "+m.getHP()+" HP");
    }

    public static void attack_2(Monster m, Hero h){
        System.out.println(m.getName() + " attacks " + h.getName());
        int dodgeChance = Rule.dodgeChance(h.getAgility());
        Random rand = new Random();
        int num = rand.nextInt(101);
        // hero dodged
        if (num < dodgeChance) {
            System.out.println(h.getName() + " successfully dodged");
            return;
        }
        int armorDamageReduction = 0;
        if(h.getArmor() != null){
            armorDamageReduction = h.getArmor().getDamageReduction();
        }
        int damage = Math.max(0, (m.getDamage()-armorDamageReduction)/20);
        h.setHP(Math.max(0,h.getHP()-damage));
        System.out.println("The damage is "+damage);
        System.out.println(h.getName()+" has "+h.getHP()+" HP");
    }

    public static void spellAttack(Hero h, Monster m){
        System.out.println("The Spells in your bags are:");
        for(int i=0;i<h.getInventory().getSpellBag().getSpellList().size();i++){
            System.out.print((i+1) + ": " + h.getInventory().getSpellBag().getSpellList().get(i).getName()+" ");
        }
        System.out.println("");
        System.out.println("Please input the spell you want:(0 means Finish)");
        Scanner sc = new Scanner(System.in);
        String temp = sc.next();
        // Assume spells less than 10
        while(temp.length()!=1 || temp.charAt(0)<48 || temp.charAt(0)>48+h.getInventory().getSpellBag().getSpellList().size()){
            System.out.println("Invalid number! Please Input Again");
            temp = sc.next();
        }
        if(temp.charAt(0) == 48){
            return;
        }else{
            spellAttack_2(h, m, h.getInventory().getSpellBag().getSpellList().get(temp.charAt(0)-49));
        }


    }

    public static void spellAttack_2(Hero h, Monster m, Spell s){

        if(s.getMpCost() > h.getMP()){
            System.out.println("MP Not Enough!");
            return;
        }
        System.out.println(h.getName() + " uses spell to attack " + m.getName());
        int damage = s.getDamage() + s.getDamage() * h.getDexterity() / 10000;
        // The effect of different spells
        if(s.getType().equals("Ice")){
            m.setDamage(m.getDamage()*9/10);
        }else if(s.getType().equals("Fire")){
            m.setDefense(m.getDefense()*9/10);
        }else if(s.getType().equals("Lightening")){
            m.setDodge(m.getDodge()*9/10);
        }
        damage = Math.max(0, damage-m.getDefense()/20);
        // Remove Spell After Use
        h.getInventory().getSpellBag().removeItem(s);
        h.setMP(h.getMP()-s.getMpCost());

    }
}
