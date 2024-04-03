public class Spell extends Item {
	public final static int MAX_DAMAGE = 10000;
	public final static int MAX_MANACOST = 10000;
	private int damage;
	private int manaCost;
	private Effect effect;
    
	public Spell(String name, int price, int level, int damage, int manaCost, Effect effect) {
		super(name, price, level);
		if (damage < 0 || damage > MAX_DAMAGE || manaCost < 0 || manaCost > MAX_MANACOST || effect == null) {
			throw new IllegalArgumentException();
		}
		this.damage = damage;
		this.manaCost = manaCost;
		this.effect = effect;
	}

    public int getDamage(){
        return damage;
    }

    public int getManaCost() {
		return manaCost;
	}
    
    public Effect getEffect() {
		return effect;
	}

    @Override
    public void print() {
		super.print();
		System.out.println("Description: \nCosts " + manaCost + " mana to cast. \n" +
				"Deals " + damage + " to the opponent. ");
		int attr = effect.getAttr();
		double dmgRate = effect.getDamageRate();
		System.out.println("Decreases the opponent's " + Attributes.getName(attr) + " by " + 
				dmgRate + ".");
	}
}
