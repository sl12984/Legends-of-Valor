public class InaccessibleSpace extends WorldSpace {
	public InaccessibleSpace() {
		super(0);
	}
	
	public boolean heroCame(Hero hero){
		System.out.println("Hero cannot enter this space.");
		return false;
    }

    public boolean monsterCame(Monster monster) {
    	System.out.println("Monster cannot enter this space.");
    	return false;
    }
    
    public void printLabel() {
    	System.out.print("I - I - I");
	}
	
    public void printContent() {
		System.out.print("| X X X |");
	}
    
    public void print() {
		System.out.print("X");
	}
}
