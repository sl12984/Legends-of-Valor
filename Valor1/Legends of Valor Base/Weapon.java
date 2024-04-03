public class Weapon extends Item {
	public final static int MAX_DAMAGE = 10000;
	public final static int MAX_HAND = 2;
	private int damage;
	private int hand;
	
	public Weapon(String name, int price, int level, int damage, int hand) {
		super(name, price, level);
		if (damage < 0 || damage > MAX_DAMAGE || hand < 0 || hand > MAX_HAND) {
			throw new IllegalArgumentException();
		}
		this.damage = damage;
		this.hand = hand;
	}

	public int getDamage() {
		return damage;
	}
	
	public int getHand() {
		return hand;
	}

    @Override
    public void print() {
		super.print();
		System.out.println("Description: \nIncreases a hero's damage by " + damage + " when equipped.");
	}
}
