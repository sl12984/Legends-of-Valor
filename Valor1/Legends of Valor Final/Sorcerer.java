public class Sorcerer extends Hero {

	public Sorcerer(String name, int mp, int strength, int agility, int dexterity, 
			int gold, int exp) {
		super(name, mp, strength, agility, dexterity, gold, exp);
	}
	
	public void levelUp() {
		levelUp(false, true, true, Rule.FAVORED_SKILL_INCREASE_RATE);
	}
}
