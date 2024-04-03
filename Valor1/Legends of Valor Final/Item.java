public class Item {
	public final static int MAX_PRICE = 10000000;
	public final static int MAX_LEVEL = 100;
	private String name;
	private int price;
	private int level;

	public Item(String name, int price, int level) {
		if (name == null || price < 0 || price > MAX_PRICE || level < 0 || level > MAX_LEVEL) {
			throw new IllegalArgumentException();
		}
		this.name = name;
		this.price = price;
		this.level = level;
	}

	public String getName() {
		return name;
	}
	
	public int getPrice() {
		return price;
	}
	
	public int getLevel() {
		return level;
	}

	public void print() {
		System.out.println("Lvl. " + level + " " + name + " $" + price);
	}
}