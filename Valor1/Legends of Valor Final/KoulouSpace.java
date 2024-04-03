public class KoulouSpace extends BonusSpace {
	public KoulouSpace(int n) {
		super(n);
	}
	
	public boolean heroCame(Hero hero, int id){
    	boolean success = super.heroCame(hero, id);
        if (success) {
        	getHero().receiveBonus(Attributes.STRENGTH, Rule.SPACE_BONUS_RATE);
        	return true;
        }
        return false;
    }
	
	public boolean heroLeft() {
		if (getHero() == null) {
			return true;
		} else {
			getHero().removeBonus(Attributes.STRENGTH, Rule.SPACE_BONUS_RATE);
		}
        return super.heroLeft();
    }
	
	public void printLabel() {
		System.out.print("K - K - K");
	}
}
