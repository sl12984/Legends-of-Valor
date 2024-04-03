import java.util.Random;

public class World extends Board {
	public final static int MARKET_ITEM_NUM = 5; //number of initial items in a market
	public final static int COMMON_SPACE_PROPORTION = 50; //50% common space
	public final static int INACCESSIBLE_SPACE_PROPORTION = 20; //20% inaccessible space
	public final static int MARKET_SPACE_PROPORTION = 30; //30% market space
	
	public World(int r, int c, int n, Item[] items, int itemNum) {
		super(r, c, n);
		Random rand = new Random();
		for (int i=0; i<getNumRow(); i++) {
			for (int j=0; j<getNumCol(); j++) {
				//starting position is always a common space
				if (i==0 && j == 0) {
					setSpace(i, j, new CommonSpace(n));
					continue;
				}
				int whichSpace = rand.nextInt(100);
				if (whichSpace < INACCESSIBLE_SPACE_PROPORTION) {
					//20% chance generating inaccessible space
					setSpace(i, j, new InaccessibleSpace());
				} else if (whichSpace < INACCESSIBLE_SPACE_PROPORTION+MARKET_SPACE_PROPORTION) {
					//30% chance generating market space
					//choose random items to put in market
					Item[] marketItems = new Item[MARKET_ITEM_NUM];
					for (int k=0; k<MARKET_ITEM_NUM; k++) {
						int item = rand.nextInt(itemNum);
						marketItems[k] = items[item];
					}
					setSpace(i, j, new MarketSpace(n, marketItems));
				} else {
					//50% chance generating common space
					setSpace(i, j, new CommonSpace(n));
				}
			}
		}
	}
}
