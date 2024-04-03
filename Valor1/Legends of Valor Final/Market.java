public class Market {
	public final static int MAX_MARKET_SIZE = 100;
	private Item[] items;
	private int itemNum; //number of items currently in the market
	
	//takes in a list of items to be put in the market
	public Market(Item[] items) {
		if (items == null) {
			throw new IllegalArgumentException();
		}
		this.items = new Item[MAX_MARKET_SIZE];
		itemNum = 0;
		for (int i=0; i< items.length; i++) {
			if (items[i] != null) {
				this.items[itemNum] = items[i];
				itemNum++;
			}
		}
	}
	
	//add item to the market, return true if successfully added, false otherwise
	public boolean addItem(Item item) {
		if (item == null) {
			return true;
		}
		if (itemNum == items.length) {
			//market is full
			return false;
		} else {
			//market is not full
			for (int i=0; i<items.length; i++) {
				//find an empty slot to place item
				if (items[i] == null) {
					items[i] = item;
					itemNum++;
					return true;
				}
			} 
		}
		return false;
	}
	
	//remove item at index i from the market, return true if successfully removed, false otherwise
	public boolean removeItem(int i) {
		if (i < 0 || i >= items.length) {
			return true;
		}
		items[i] = null;
		itemNum--;
		return true;
	}
	
	//get item at index i
	public Item getItem(int i) {
		if (i < 0 || i > items.length) {
			return null;
		}
		return items[i];
	}
	
	//returns whether the market is full
	public boolean isFull() {
		return itemNum == items.length;
	}
	
	public void print() {
		System.out.println("Market menu");
		for (int i=0; i<items.length; i++) {
			if (items[i] != null) {
				System.out.print("(" + i + ") ");
				items[i].print();
			}
		}
	}
}
