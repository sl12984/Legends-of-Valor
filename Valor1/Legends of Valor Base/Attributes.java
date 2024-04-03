public class Attributes {
	public final static int NONE = 0;
	public final static int HP = 1;
	public final static int MP = 2;
	public final static int STRENGTH = 3;
	public final static int DEXTERITY = 4;
	public final static int AGILITY = 5;
	public final static int DAMAGE = 6;
	public final static int DEFENSE = 7;
	public final static int DODGECHANCE = 8;
	
	private Attributes() {
		
	}
	
	//checks if a value is among the list above
	public static boolean isValid(int attr) {
		if (attr >= NONE && attr <= DODGECHANCE) {
			return true;
		} else {
			return false;
		}
	}
	
	public static String getName(int attr) {
		switch (attr) {
			case NONE: 
				return "none";
			case HP: 
				return "hp";
			case MP: 
				return "mp";
			case STRENGTH: 
				return "strength";
			case DEXTERITY: 
				return "dexterity";
			case AGILITY: 
				return "agility";
			case DAMAGE:
				return "damage";
			case DEFENSE:
				return "defense";
			case DODGECHANCE:
				return "dodge chance";
			default:
				return "invalid attribute";
		}
	}
}
