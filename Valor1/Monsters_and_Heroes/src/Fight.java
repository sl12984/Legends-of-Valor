import java.util.Scanner;

public class Fight {
    HeroParty h;
    MonsterParty m;
    public Fight(HeroParty h, MonsterParty m){
        this.h = h;
        this.m = m;
    }

    public void run(){
        for(int i=0; i<h.size();i++){
            if(h.getHeroParty().get(i).getHP() == 0){
                continue;
            }
            // Check the first monster alive
            int flag = -1;
            for(int j=0;j<m.size();j++){
                if(m.getMonsterParty().get(j).getHP() > 0){
                    flag = j;
                    break;
                }
            }
            if(flag == -1) break;
            Hero hero = h.getHeroParty().get(i);
            Monster monster = m.getMonsterParty().get(flag);
            // Hero Act
            System.out.println(hero.getName()+", Please Select the action you want:");
            System.out.println("1. Attack 2. Cast a Spell 3. Drink a Potion 4. Equip Weapon 5. Equip Armor Q/q. quit I/i. information");
            Scanner sc = new Scanner(System.in);
            String temp = sc.next();
            while(temp.length()!=1 || temp.charAt(0) < 49 || (temp.charAt(0) > 53&& temp.charAt(0)!='Q'
                            && temp.charAt(0)!='q' && temp.charAt(0)!='I' && temp.charAt(0)!='i')){
                System.out.println("Input should between 1 and 5 or q or i!");
                temp = sc.next();
            }
            if(temp.charAt(0) == 49){
                Attack.attack_1(hero, monster);
            }else if(temp.charAt(0) == 50){
                Attack.spellAttack(hero, monster);
            }else if(temp.charAt(0) == 51){
                hero.drinkPotion();
            }else if(temp.charAt(0) == 52){
                hero.equipWeapon();
            }else if(temp.charAt(0) == 53){
                hero.equipArmor();
            }else if(temp.charAt(0) == 'Q' || temp.charAt(0) == 'q'){
                System.exit(0);
            }else if (temp.charAt(0) == 'I' || temp.charAt(0) == 'i'){
                display(h);
                display(m);
            }


        }

        for(int i=0; i<m.size();i++){
            if(m.getMonsterParty().get(i).getHP() == 0){
                continue;
            }
            // Check the first monster alive
            int flag = -1;
            for(int j=0;j<h.size();j++){
                if(h.getHeroParty().get(j).getHP() > 0){
                    flag = j;
                    break;
                }
            }
            if(flag == -1) break;
            Hero hero = h.getHeroParty().get(flag);
            Monster monster = m.getMonsterParty().get(i);
            Attack.attack_2(monster, hero);

        }
        // Regain HP and MP
        for(int i=0;i<h.size();i++){
            h.getHeroParty().get(i).setHP(Math.min(h.getHeroParty().get(i).getHP() + h.getHeroParty().get(i).getHP()/10, h.getHeroParty().get(i).getLevel()*100));
            h.getHeroParty().get(i).setMP(h.getHeroParty().get(i).getMP() + h.getHeroParty().get(i).getMP()/10);
        }
    }

    public void display(HeroParty h){
        System.out.println("Here's your current heroes info");
        String str = Formatter.space("Number", 10)
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

        int number = 1;
        for (Hero hero: h.getHeroParty()) {
            str += Formatter.space(Integer.toString(number), 10) + h.toString() + "\n";
            number++;
        }
        System.out.println(str);
    }

    public void display(MonsterParty m){
        System.out.println("Here's your current monsters info");
        String str = Formatter.space("Type", 12)
                + Formatter.space("Name", 20)
                + Formatter.space("Level", 10)
                + Formatter.space("HP", 10)
                + Formatter.space("Damage", 10)
                + Formatter.space("Defense", 10)
                + Formatter.space("Dodge", 10)
                + "\n";
        for (Monster monster: m.getMonsterParty()) {
            str += monster.toString() + "\n";
        }
        System.out.println(str);
    }
}

