public class Armor extends Item{
	public final static int MAX_DAMAGE_REDUCTION = 10000;
	private int damageReduction;
	
	public Armor(String name, int price, int level, int damageReduction) {
		super(name, price, level);
		if (damageReduction < 0 || damageReduction > MAX_DAMAGE_REDUCTION) {
			throw new IllegalArgumentException();
		}
		this.damageReduction = damageReduction;
	}

    public int getDamageReduction() {
        return damageReduction;
    }

    @Override
    public void print() {
		super.print();
		System.out.println("Description: \n"
				+ "Decreases damage taken by a hero by " + damageReduction + " when equipped.");
	}
}
