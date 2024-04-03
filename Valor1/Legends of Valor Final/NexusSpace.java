public class NexusSpace extends WorldSpace {
	private Market market;
	
	public NexusSpace(int n, Item[] items) {
		super(n);
		if (items == null) {
			market = null;
		} else {
			market = new Market(items);
		}
	}
	
	public Market getMarket() {
		return market;
	}
	
	public void printLabel() {
		System.out.print("N - N - N");
	}
	
	public void print() {
		if (getCurrentType() == 0) {
			System.out.print("M");
		} else {
			System.out.print("O");
		}
	}
}
