import java.util.Random;
import java.util.Scanner;

public class Hero extends Character implements Fightable {
	public final static int MAX_MP = 10000;
	public final static int MAX_STRENGTH = 10000;
	public final static int MAX_DEXTERITY = 10000;
	public final static int MAX_AGILITY = 1000;
	public final static int MAX_GOLD= 1000000000;
	public final static int MAX_EXP = 9;
	public final static int MAX_INVENTORY_SIZE = 100;
	
	private int preFightHp; //preFight variables are constant during battles, representing original
	//values when battle starts
	private int preFightMp;
	private int preFightStrength;
	private int preFightDexterity;
	private int preFightAgility;
	private int level;
	private int exp;
	private int hp;
	private int mp;
	private int strength;
	private int dexterity;
	private int agility;
	private int gold;
	private Inventory inventory;
	private Weapon weapon;
	private Armor armor;
	private boolean isDead;
	private Scanner scan;
	
	private int row;
	private int col;
	private int initialRow;
	private int initialCol;
	
	public Hero(String name, int mp, int strength, int agility, int dexterity, 
			int gold, int exp) {
		super(name);
		if ( mp < 0 || mp > MAX_MP || strength < 0 ||
				strength > MAX_STRENGTH || dexterity < 0 || dexterity > MAX_DEXTERITY ||
				agility < 0 || agility > MAX_AGILITY || gold < 0 || gold > MAX_GOLD ||
				exp < 0 || exp > MAX_EXP) {
			throw new IllegalArgumentException();
		}
		preFightHp = Rule.levelToHP(level);
		preFightMp = mp;
		preFightStrength = strength;
		preFightDexterity = dexterity;
		preFightAgility = agility;
		this.level = 1;
		this.exp = exp;
		this.hp = Rule.levelToHP(level);
		this.mp = mp;
		this.strength = strength;
		this.dexterity = dexterity;
		this.agility = agility;
		this.gold = gold;
		this.inventory = new Inventory(MAX_INVENTORY_SIZE);
		this.weapon = null;
		this.armor = null;
		this.isDead = false;
		this.scan = new Scanner(System.in);
		this.row = -1;
		this.col = -1;
		
		//add default items to inventory
		Armor defaultArmor = new Armor("Default armor", 0, 1, 300);
		Weapon defaultWeapon = new Weapon("Default weapon", 0, 1, 1000, 1);
		Potion defaultPotion = new Potion("Default hp potion", 0, 1, 30, "Health");
		Spell defaultSpell = new FireSpell("Default fire spell", 0, 1, 800, 100);
		inventory.addItem(defaultArmor);
		inventory.addItem(defaultWeapon);
		inventory.addItem(defaultPotion);
		inventory.addItem(defaultSpell);
	}
	
	public Effect[] attack() {
		if (isDead) {
			System.out.println(getName() + " has fainted and cannot attack.");
			return null;
		}
		
		Effect[] effects = new Effect[1];
		if (weapon == null) {
			effects[0] = new Effect(Attributes.HP, Rule.heroDamage(strength, 0));
		} else {
			effects[0] = new Effect(Attributes.HP, Rule.heroDamage(strength, weapon.getDamage()));
		}
		System.out.println(getName() + " attacked for" + effects[0] + " Damage!");
		return effects;
	}
	
	public Effect[] castSpell() {
		if (isDead) {
			System.out.println(getName() + " has fainted and cannot cast a spell.");
			return null;
		}
		inventory.printSpell();
		System.out.print("Please enter the spell you want to use based on"
				+ " the index at the left (-1 for none of them): ");
		int choice = scan.nextInt();
		if (choice == -1) {
			return null;
		} else {
			Item item = inventory.getItem(choice);
			Spell toCast = null;
			if (!(item instanceof Spell)) {
				System.out.println("This input is invalid.");
				return null;
			} else {
				toCast = (Spell) item;
			}
			
			int manaCost = toCast.getManaCost();
			if (mp < manaCost) {
				System.out.print("You don't have enough mana to cast this spell.");
				return null;
			}
			mp -= manaCost;
			inventory.removeItem(choice);
			Effect[] effects = new Effect[2];
			effects[0] = new Effect(Attributes.HP, Rule.spellDamage(dexterity, toCast.getDamage()));
			effects[1] = toCast.getEffect();
			System.out.println(getName() + " casted " + toCast.getName() + "!");
			return effects;
		}
	}
	
	public boolean usePotion() {
		if (isDead) {
			System.out.println(getName() + " has fainted and cannot use a potion.");
			return false;
		}
		inventory.printPotion();
		System.out.print("Please enter the potion you want to use based on"
				+ " the index at the left (-1 for none of them): ");
		int choice = scan.nextInt();
		if (choice == -1) {
			return false;
		} else {
			Item item = inventory.getItem(choice);
			Potion toUse = null;
			if (!(item instanceof Potion)) {
				System.out.println("This input is invalid.");
				return false;
			} else {
				toUse = (Potion) item;
			}
			
			System.out.println(getName() + " used " + toUse.getName() + "!");
			for (int i=0; i<toUse.getAttrNum(); i++) {
				int attr = toUse.getAttr(i);
				if (attr == Attributes.HP) {
					hp += toUse.getBuff();
				} else if (attr == Attributes.MP) {
					mp += toUse.getBuff();
				} else if (attr == Attributes.STRENGTH) {
					strength += toUse.getBuff();
				} else if (attr == Attributes.DEXTERITY) {
					dexterity += toUse.getBuff();
				} else if (attr == Attributes.AGILITY) {
					agility += toUse.getBuff();
				} else {
					continue;
				}
				System.out.println(getName() + " increased "
						+ Attributes.getName(attr) + " by " + toUse.getBuff() + "!");
			}
			inventory.removeItem(choice);
			return true;
		}
	}
	
	public boolean equip() {
		if (isDead) {
			System.out.println(getName() + " has fainted and cannot equip a weapon or armor.");
			return false;
		}
		inventory.printWeaponArmor();
		System.out.print("Please enter the weapon or armor you want to use based on"
				+ " the index at the left (-1 for none of them): ");
		int choice = scan.nextInt();
		if (choice == -1) {
			return false;
		} else {
			Item toEquip = inventory.getItem(choice);
			if (toEquip instanceof Weapon) {
				weapon = (Weapon)toEquip;
				System.out.println(getName() + " equipped " + toEquip.getName() + "!");
				return true;
			} else if (toEquip instanceof Armor) {
				armor = (Armor)toEquip;
				System.out.println(getName() + " equipped " + toEquip.getName() + "!");
				return true;
			} else {
				System.out.println("This input is invalid.");
				return false;
			}
		}
	}
	
	public boolean receiveDmg(Effect[] damages) {
		if (damages == null) {
			throw new IllegalArgumentException();
		}
		if (isDead) {
			System.out.println(getName() + " has fainted already.");
			return false;
		}
		Random rand = new Random();
		int dodge = rand.nextInt(Rule.AGILITY_SCALE);
		if (dodge < agility) {
			System.out.println(getName() + " dodged the attack!");
			return true;
		} else {
			for (int i=0; i<damages.length; i++) {
				if (damages[i] != null) {
					int attr = damages[i].getAttr();
					int dmg = damages[i].getDamage();
					double rate = damages[i].getDamageRate();
					if (rate != 0) {
						//damage is scaled to the attribute
						if (attr == Attributes.HP) {
							dmg = (int)(hp * rate);
						} else {
							continue;
						}
					}
					
					if (attr == Attributes.HP) {
						int reduction = 0;
						if (armor != null) {
							reduction += armor.getDamageReduction();
						}
						dmg = dmg - reduction;
						if (dmg < 0) {
							dmg = 0;
						}
						hp -= dmg;
						System.out.println(getName() + " took " + dmg + " damage!");
					} else {
						continue;
					}
				}
			}
			
			if (hp <= 0) {
				hp = 0;
				System.out.println(getName() + " fainted!");
				isDead = true;
			}
			return true;
		}
	}
	
	public void receiveBonus(int attr, double buff) {
		if (buff < 1) {
			return;
		}
		if (attr == Attributes.STRENGTH) {
			strength = (int) (strength * buff);
		} else if (attr == Attributes.DEXTERITY) {
			dexterity = (int) (dexterity * buff);
		} else if (attr == Attributes.AGILITY) {
			agility = (int) (agility * buff);
		} else {
			System.out.println("Invalid attribute.");
			return;
		}
		System.out.println(getName() + "'s "
				+ Attributes.getName(attr) + " is increased by " + (buff-1) + "!");
	}
	
	public void removeBonus(int attr, double buff) {
		if (buff < 1) {
			return;
		}
		if (attr == Attributes.STRENGTH) {
			strength = (int) (strength / buff);
		} else if (attr == Attributes.DEXTERITY) {
			dexterity = (int) (dexterity / buff);
		} else if (attr == Attributes.AGILITY) {
			agility = (int) (agility / buff);
		} else {
			System.out.println("Invalid attribute.");
			return;
		}
		System.out.println(getName() + "'s "
				+ Attributes.getName(attr) + " has returned to normal.");
	}
	
	public boolean recover() {
		if (isDead) {
			return false;
		}
		hp = (int)(hp * (1+Rule.HP_REGAIN_RATE));
		mp = (int)(mp * (1+Rule.MP_REGAIN_RATE));
		return true;
	}
	
	public boolean isDead() {
		return isDead;
	}
	
	public void reset() {
		hp = preFightHp;
		mp = preFightMp;
		strength = preFightStrength;
		dexterity = preFightDexterity;
		agility = preFightAgility;
		weapon = null;
		armor = null;
		isDead = false;
	}
	
	public void gainExp(int amount) {
		if (amount < 0) {
			throw new IllegalArgumentException();
		}
		if (isDead) {
			return;
		}
		exp += amount;
		System.out.println(getName() + " gained " + amount + " exp!");
		reset();
		while (exp >= Rule.levelToExp(level)) {
			levelUp();
			exp -= Rule.levelToExp(level);
		}
	}
	
	public void gainGold(int amount) {
		if (amount < 0) {
			throw new IllegalArgumentException();
		}
		if (isDead) {
			return;
		}
		gold += amount;
		System.out.println(getName() + " gained " + amount + " gold!");
	}
	
	public void levelUp() {
		reset();
		level++;
		preFightHp = Rule.levelToHP(level);
		preFightMp = (int)(mp * (1+Rule.MP_LEVEL_UP_RATE));
		preFightStrength = (int)(strength * (1+Rule.SKILL_LEVEL_UP_RATE));
		preFightDexterity = (int)(dexterity * (1+Rule.SKILL_LEVEL_UP_RATE));
		preFightAgility = (int)(agility * (1+Rule.SKILL_LEVEL_UP_RATE));
		hp = preFightHp;
		mp = preFightMp;
		strength = preFightStrength;
		dexterity = preFightDexterity;
		agility = preFightAgility;
		System.out.println(getName() + " leveled up to level " + level + "!");
	}
	
	//if strength is true, increase strength by "bouns", same for the other two attr
	public void levelUp(boolean str, boolean dex, boolean agi, double bonus) {
		if (bonus < 0) {
			throw new IllegalArgumentException();
		}
		reset();
		level++;
		preFightHp = Rule.levelToHP(level);
		preFightMp = (int)(mp * (1+Rule.MP_LEVEL_UP_RATE));
		if (str) {
			preFightStrength = (int)(strength * (1+Rule.SKILL_LEVEL_UP_RATE+bonus));
		} else {
			preFightStrength = (int)(strength * (1+Rule.SKILL_LEVEL_UP_RATE));
		}
		if (dex) {
			preFightDexterity = (int)(dexterity * (1+Rule.SKILL_LEVEL_UP_RATE+bonus));
		} else {
			preFightDexterity = (int)(dexterity * (1+Rule.SKILL_LEVEL_UP_RATE));
		}
		if (agi) {
			preFightAgility = (int)(agility * (1+Rule.SKILL_LEVEL_UP_RATE+bonus));
		} else {
			preFightAgility = (int)(agility * (1+Rule.SKILL_LEVEL_UP_RATE));
		}
		
		hp = preFightHp;
		mp = preFightMp;
		strength = preFightStrength;
		dexterity = preFightDexterity;
		agility = preFightAgility;
		System.out.println(getName() + " leveled up to level " + level + "!");
	}
	
	public int getLevel() {
		return level;
	}
	
	public boolean buy(Item item) {
		if (gold - item.getPrice() < 0) {
			System.out.println(getName() + " doesn't have enough gold to buy " 
					+ item.getName() + ".");
			return false;
		}
		if (level < item.getLevel()) {
			System.out.println(getName() + "'s level is not high enough to buy " 
					+ item.getName() + ".");
			return false;
		}
		if (inventory.addItem(item)) {
			gold -= item.getPrice();
			System.out.println(getName() + " bought " + item.getName() + ".");
			return true;
		}
		System.out.println(getName() + "'s inventory is full. Purchase failed.");
		return false;
	}
	
	public Item sell() {
		if (inventory.getNum() == 0) {
			System.out.println(getName() + " doesn't have any item in its inventory.");
			return null;
		}
		printInventory();
		System.out.print("Which item do you want to sell?"
				+ " (Enter the index on the left) ");
		int i = scan.nextInt();
		Item item = inventory.getItem(i);
		while (item == null) {
			System.out.println("This input is invalid. Please try again.");
			System.out.print("Which item do you want to sell?"
					+ " (Enter the index on the left) ");
			i = scan.nextInt();
			item = inventory.getItem(i);
		}
		
		gold += item.getPrice()/2;
		inventory.removeItem(i);
		System.out.println(getName() + " sold " + item.getName() + ".");
		return item;
	}
	
	public void printHP() {
		System.out.println("Lvl. " + level + " " + getName() + " HP: " + hp);
	}
	
	public void printCombatStats() {
		String weaponName = "None";
		if (weapon != null) {
			weaponName = weapon.getName();
		}
		String armorName = "None";
		if (armor != null) {
			armorName = armor.getName();
		}
		System.out.println("Lvl. " + level + " " + getName() + " HP: " + hp + 
				" | MP: " + mp + " | Strength: " + strength + " | Dexterity: " + dexterity + 
				" | Agility: " + agility + 
				" | Equipped (weapon, armor): " + weaponName + ", " + armorName);
	}
	
	public void printInventory() {
		System.out.println(getName() + "'s gold: " + gold);
		System.out.println(getName() + "'s inventory:");
		inventory.print();
	}
	
	public void printStats() {
		System.out.println("Lvl. " + level + " " + getName());
		System.out.println("HP: " + hp);
		System.out.println("MP: " + mp);
		System.out.println("Strength: " + strength);
		System.out.println("Dexterity: " + dexterity);
		System.out.println("Agility: " + agility);
		System.out.println("Gold: " + gold);
		System.out.println("Inventory: ");
		inventory.print();
	}
	
	public int getRow(){
		return row;
	}
	public int getCol(){
		return col;
	}
	public int getInitialRow(){
		return initialRow;
	}
	public int getInitialCol(){
		return initialCol;
	}
	
	public void setRow(int r){
		row = r;
	}
	public void setCol(int c){
		col = c;
	}
	public void setInitialRow(int r){
		initialRow = r;
	}
	public void setInitialCol(int c){
		initialCol = c;
	}

	public int getHP() {
		return hp;
	}

	public int getMP() {
		return mp;
	}

	public int getStrength() {
		return strength;
	}

	public int getDexterity() {
		return dexterity;
	}

	public int getAgility() {
		return agility;
	}

	public int getGold() {
		return gold;
	}

	public int getExperience() {
		return exp;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setMP(int MP) {
		this.mp = MP;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}
}
