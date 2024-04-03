public class MarketSpace extends WorldSpace {
	private Market market;
	
	public MarketSpace(int n, Item[] items) {
		super(n);
		if (items == null) {
			throw new IllegalArgumentException();
		}
		market = new Market(items);
	}
	
	public Market getMarket() {
		return market;
	}
	
	public void print() {
		if (getCurrentType() == 0) {
			System.out.print("M");
		} else {
			System.out.print("O");
		}
	}
}
