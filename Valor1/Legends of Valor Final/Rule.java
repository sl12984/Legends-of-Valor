public class Rule {
	public final static int LEVEL_HP_CONVERT_RATE = 100;
	public final static int LEVEL_EXP_CONVERT_RATE = 10;
	
	public final static double HERO_DAMAGE_RATE = 0.05;
	public final static int DEXTERITY_SCALE = 10000;
	public final static int EXP_GAIN_RATE = 10;
	public final static int GOLD_GAIN_RATE = 100;
	public final static int AGILITY_SCALE = 1000; //dodge rate = agility/1000
	public final static double MP_LEVEL_UP_RATE = 0.1; //new mp = mp*1.1
	public final static double SKILL_LEVEL_UP_RATE = 0.05; //new skill = skill*1.05
	public final static double HP_REGAIN_RATE = 0.1; //regain 10% hp at the end of each round
	public final static double MP_REGAIN_RATE = 0.1; //regain 10% mp at the end of each round
	
	public final static int DODGECHANCE_SCALE = 100; //dodge rate = dodgechance/100
	public final static double SKILL_LOSS_RATE = 0.1;
	public final static double FAVORED_SKILL_INCREASE_RATE = 0.05;
	public final static double SPACE_BONUS_RATE = 1.1; //entering special space will increase attributes
	//by 10%
	
	private Rule() {
		
	}
	
	//takes in level, returns hp value
	public static int levelToHP(int level) {
		return level * LEVEL_HP_CONVERT_RATE;
	}
	
	//takes in level, returns exp required to level up
	public static int levelToExp(int level) {
		return level * LEVEL_EXP_CONVERT_RATE;
	}
	
	//takes in a hero's strength and their equipped weapon damage, returns the damage they will deal
	public static int heroDamage(int strength, int weaponDamage) {
        return (int)((strength + weaponDamage) * HERO_DAMAGE_RATE);
    }
	
	//takes in a hero's dexterity and the spell damage, returns the damage the hero will deal
	public static int spellDamage(int dexterity, int spellDamage) {
        return (int)((spellDamage + (dexterity/DEXTERITY_SCALE)*spellDamage)*HERO_DAMAGE_RATE);
    }

	//takes in monster's level, returns the amount of gold a hero will gain from winning
    public static int goldGain(int level) {
        return level * GOLD_GAIN_RATE;
    }

    //takes in monster's level, returns the amount of exp a hero will gain from winning
    public static int expGain(int level) {
        return level * EXP_GAIN_RATE;
    }
}
