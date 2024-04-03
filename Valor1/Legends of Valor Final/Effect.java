public class Effect {
	private int attr;
	private int damage; //flat damage
	private double damageRate; //damage scaled with the value of the attribute 
	//(ex: damage = defense * 0.1 -> damageRate = 0.1)
	
	public Effect(int attr, int damage) {
		if (!Attributes.isValid(attr) || damage <= 0) {
			throw new IllegalArgumentException();
		}
		this.attr = attr; //attribute
		this.damage = damage;
		this.damageRate = 0;
	}
	
	public Effect(int attr, double damageRate) {
		if (!Attributes.isValid(attr) || damageRate <= 0 || damageRate > 1) {
			throw new IllegalArgumentException();
		}
		this.attr = attr;
		this.damageRate = damageRate;
		this.damage = 0;
	}
	
	public int getAttr() {
		return attr;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public double getDamageRate() {
		return damageRate;
	}
}
