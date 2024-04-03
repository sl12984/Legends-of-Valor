public class LightningSpell extends Spell {

	public LightningSpell(String name, int price, int level, int damage, int manaCost) {
		super(name, price, level, damage, manaCost, new Effect(Attributes.DODGECHANCE, Rule.SKILL_LOSS_RATE));
	}
}
