public class CommonSpace extends WorldSpace {
	public CommonSpace(int n) {
		super(n);
	}
	
	public void print() {
		if (getCurrentType() == 0) {
			System.out.print("_");
		} else {
			System.out.print("O");
		}
	}
}
