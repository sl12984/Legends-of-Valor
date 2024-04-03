public class FireSpell extends Spell {

	public FireSpell(String name, int price, int level, int damage, int manaCost) {
		super(name, price, level, damage, manaCost, new Effect(Attributes.DEFENSE, Rule.SKILL_LOSS_RATE));
	}
}
