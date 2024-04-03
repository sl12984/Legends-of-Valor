import java.util.Random;

public class Monster extends Character implements Fightable {
	public final static int MAX_LEVEL = 100;
	public final static int MAX_DAMAGE = 10000;
	public final static int MAX_DEFENSE = 10000;
	public final static int MAX_DODGECHANCE = 99;
	
	private int preFightHp; //preFight variables are constant during battles, representing original
	//values when battle starts
	private int preFightDamage;
	private int preFightDefense;
	private int preFightDodgechance;
	private int level;
	private int hp;
	private int damage;
	private int defense;
	private int dodgechance;
	private boolean isDead;
	private int row;
	private int col;
	
	public Monster(String name, int level, int damage, int defense, int dodgechance) {
		super(name);
		if (level < 0 || level > MAX_LEVEL ||
				damage < 0 || damage > MAX_DAMAGE || defense < 0 || defense > MAX_DEFENSE ||
				dodgechance < 0 || dodgechance > MAX_DODGECHANCE) {
			throw new IllegalArgumentException();
		}
		preFightHp = Rule.levelToHP(level);
		preFightDamage = damage;
		preFightDefense = defense;
		preFightDodgechance = dodgechance;
		this.level = level;
		this.hp = Rule.levelToHP(level);
		this.damage = damage;
		this.defense = defense;
		this.dodgechance = dodgechance;
		isDead = false;
	}
	
	public Effect[] attack() {
		if (isDead) {
			System.out.println(getName() + " has fainted and cannot attack.");
			return null;
		}
		Effect[] effects = new Effect[1];
		effects[0] = new Effect(Attributes.HP, damage);
		System.out.println(getName() + " attacked!");
		return effects;
	}
	
	public Effect[] castSpell() {
		System.out.println("Monster cannot cast spell.");
		return null;
	}
	
	public boolean usePotion() {
		System.out.println("Monster cannot use potion.");
		return false;
	}
	
	public boolean equip() {
		System.out.println("Monster cannot equip weapon or armor.");
		return false;
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
		int dodge = rand.nextInt(Rule.DODGECHANCE_SCALE);
		if (dodge < dodgechance) {
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
						} else if (attr == Attributes.DAMAGE) {
							dmg = (int)(damage * rate);
						} else if (attr == Attributes.DEFENSE) {
							dmg = (int)(defense * rate);
						} else if (attr == Attributes.DODGECHANCE) {
							dmg = (int)(dodgechance * rate);
						} else {
							continue;
						}
					}
					
					if (attr == Attributes.HP) {
						dmg = dmg - defense;
						if (dmg < 0) {
							dmg = 0;
						}
						hp -= dmg;
					} else if (attr == Attributes.DAMAGE) {
						damage -= dmg;
						if (damage < 0) {
							damage = 0;
						}
					} else if (attr == Attributes.DEFENSE) {
						defense -= dmg;
						if (defense < 0) {
							defense = 0;
						}
					} else if (attr == Attributes.DODGECHANCE) {
						dodgechance -= dmg;
						if (dodgechance < 0) {
							dodgechance = 0;
						}
					} else {
						continue;
					}
					if (attr == Attributes.HP) {
						System.out.println(getName() + " took " + dmg + " damage!");
					} else {
						System.out.println(getName() + "'s " + Attributes.getName(attr) + 
								" is decreased by " + dmg + "!");
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
	
	public boolean recover() {
		System.out.println("Monster cannot recover.");
		return false;
	}
	
	public void reset() {
		hp = preFightHp;
		damage = preFightDamage;
		defense = preFightDefense;
		dodgechance = preFightDodgechance;
		isDead = false;
	}
	
	public boolean isDead() {
		return isDead;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void printHP() {
		System.out.println("Lvl. " + level + " " + getName() + " HP: " + hp);
	}
	
	public void printCombatStats() {
		System.out.println("Lvl. " + level + " " + getName() + " HP: " + hp + 
				" | Damage: " + damage + " | Defense: " + defense + " | Dodge chance: " + dodgechance);
	}
	
	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getDodge() {
		return dodgechance;
	}

	public void setDodge(int dodge) {
		this.dodgechance = dodge;
	}

	public int getHP() {
		return hp;
	}

	// HP setter
	public void setHP(int HP) {
		this.hp = HP;
	}

	// lose hp
	public void minusHP(int hp) {
		this.hp -= hp;
	}

    public int getRow(){
        return row;
    }
    public int getCol(){
        return col;
    }

	public void setRow(int row){
		this.row = row;
	}

	public void setCol(int col){
		this.col = col;
	}

	public void setSpace(int row, int col){
		this.row = row;
		this.col = col;
	}
}
