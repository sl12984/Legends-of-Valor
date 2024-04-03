public class Space {
	
	public final static int TYPENUMCEILING = 10;
	private int contentTypeNum; //how many types of content can be placed in the space
	//can not hold more than 10 types
	private int currentType; //0 means the space is empty. 1 means the space now contains 
	//content of type 1 and so on
	//currentType cannot be a number greater than contentTypeNum
	
	//input n indicates how many types of content can be placed in the space 
	//(ex: TicTacToe has 2 types: O and X)
	public Space(int n) {
		if (n < 0 || n > TYPENUMCEILING) {
			throw new IllegalArgumentException("Input invalid");
		}
		this.contentTypeNum = n;
		this.currentType = 0;
	}
	
	public int getContentTypeNum() {
		return this.contentTypeNum;
	}
	
	public int getCurrentType() {
		return this.currentType;
	}
	
	//takes in the type of the content to be placed in the space
	//return true if successfully placed
	public boolean setType(int type) {
		if (type < 0 || type > this.contentTypeNum) {
			return false;
		}
		this.currentType = type;
		return true;
	}
	
	public void print() {
		System.out.print(currentType);
	}
}
