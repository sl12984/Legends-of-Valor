public class Paladin extends Hero {
	
	public Paladin(String name, int mp, int strength, int agility, int dexterity, 
			int gold, int exp) {
		super(name, mp, strength, agility, dexterity, gold, exp);
	}
	
	public void levelUp() {
		levelUp(true, true, false, Rule.FAVORED_SKILL_INCREASE_RATE);
	}
}
