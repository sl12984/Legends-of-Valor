public class IceSpell extends Spell {
	
	public IceSpell(String name, int price, int level, int damage, int manaCost) {
		super(name, price, level, damage, manaCost, new Effect(Attributes.DAMAGE, Rule.SKILL_LOSS_RATE));
	}
}
