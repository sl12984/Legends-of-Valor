public class PlainSpace extends WorldSpace {
	public PlainSpace(int n) {
		super(n);
	}
	
	public void printLabel() {
		System.out.print("P - P - P");
	}
	
	public void print() {
		if (getCurrentType() == 0) {
			System.out.print("_");
		} else {
			System.out.print("O");
		}
	}
}
