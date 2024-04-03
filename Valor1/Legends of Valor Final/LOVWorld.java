import java.util.Random;

public class LOVWorld extends Board {
	public final static int MARKET_ITEM_NUM = 5; //number of initial items in a market
	
	public LOVWorld(int r, int c, int n, Item[] items, int itemNum) {
		super(r, c, n);
		Random rand = new Random();
		
		//set monster nexus
		for (int j=0; j<getNumCol(); j++) {
			if (j != 2 && j != 5) {
				setSpace(0, j, new NexusSpace(n, null));
			}
		}
		
		//set hero nexus
		for (int j=0; j<getNumCol(); j++) {
			if (j != 2 && j != 5) {
				setSpace(getNumRow()-1, j, new NexusSpace(n, items));
			}
		}
		
		//set inaccessible space
		for (int i=0; i<getNumRow(); i++) {
			setSpace(i, 2, new InaccessibleSpace());
		}
		for (int i=0; i<getNumRow(); i++) {
			setSpace(i, 5, new InaccessibleSpace());
		}
		
		//used to check if the generated map has every type of Space
		boolean hasBush = false;
		boolean hasCave = false;
		boolean hasKoulou = false;
		
		while (!hasBush || !hasCave || !hasKoulou) {
			hasBush = false;
			hasCave = false;
			hasKoulou = false;
			
			//set top lane
			for (int i=1; i<getNumRow()-1; i++) {
				for (int j=0; j<2; j++) {
					int whichSpace = rand.nextInt(6);
					if (whichSpace < 1) {
						setSpace(i, j, new BushSpace(n));
						hasBush = true;
					} else if (whichSpace < 2) {
						setSpace(i, j, new CaveSpace(n));
						hasCave = true;
					} else if (whichSpace < 3) {
						setSpace(i, j, new KoulouSpace(n));
						hasKoulou = true;
					} else {
						setSpace(i, j, new PlainSpace(n));
					}
				}
			}
			
			//set mid lane
			for (int i=1; i<getNumRow()-1; i++) {
				for (int j=3; j<5; j++) {
					int whichSpace = rand.nextInt(6);
					if (whichSpace < 1) {
						setSpace(i, j, new BushSpace(n));
						hasBush = true;
					} else if (whichSpace < 2) {
						setSpace(i, j, new CaveSpace(n));
						hasCave = true;
					} else if (whichSpace < 3) {
						setSpace(i, j, new KoulouSpace(n));
						hasKoulou = true;
					} else {
						setSpace(i, j, new PlainSpace(n));
					}
				}
			}
			
			//set bot lane
			for (int i=1; i<getNumRow()-1; i++) {
				for (int j=6; j<getNumCol(); j++) {
					int whichSpace = rand.nextInt(6);
					if (whichSpace < 1) {
						setSpace(i, j, new BushSpace(n));
						hasBush = true;
					} else if (whichSpace < 2) {
						setSpace(i, j, new CaveSpace(n));
						hasCave = true;
					} else if (whichSpace < 3) {
						setSpace(i, j, new KoulouSpace(n));
						hasKoulou = true;
					} else {
						setSpace(i, j, new PlainSpace(n));
					}
				}
			}
		}
	}
	
	public void print() {
		for (int i=0; i<getNumRow(); i++) {
			//print top label
			for (int j=0; j<getNumCol(); j++) {
				WorldSpace space = (WorldSpace) getSpace(i, j);
				space.printLabel();
				System.out.print("   ");
			}
			System.out.println();
			
			//print content
			for (int j=0; j<getNumCol(); j++) {
				WorldSpace space = (WorldSpace) getSpace(i, j);
				space.printContent();
				System.out.print("   ");
			}
			System.out.println();
			
			//print bottom label
			for (int j=0; j<getNumCol(); j++) {
				WorldSpace space = (WorldSpace) getSpace(i, j);
				space.printLabel();
				System.out.print("   ");
			}
			System.out.println("\n");
		}
	}
}
