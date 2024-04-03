public class Party {
	private Fightable[] party;
	
	//the elements in the party must not be null
	public Party(Fightable[] party) {
		if (party == null) {
			throw new IllegalArgumentException();
		}
		for (int i=0; i<party.length; i++) {
			if (party[i] == null) {
				throw new IllegalArgumentException();
			}
		}
		this.party = party;
	}
	
	//make the i'th member attack
	public Effect[] attack(int i) {
		if (i < 0 || i >= party.length) {
			throw new IllegalArgumentException();
		}
		return party[i].attack();
	}
	
	//make the i'th memeber cast spell
	public Effect[] castSpell(int i) {
		if (i < 0 || i >= party.length) {
			throw new IllegalArgumentException();
		}
		return party[i].castSpell();
	}
	
	//make the i'th member use potion
	public boolean usePotion(int i) {
		if (i < 0 || i >= party.length) {
			throw new IllegalArgumentException();
		}
		return party[i].usePotion();
	}
	
	//make the i'th member equip
	public boolean equip(int i) {
		if (i < 0 || i >= party.length) {
			throw new IllegalArgumentException();
		}
		return party[i].equip();
	}
	
	//make the i'th member recieve damage
	public boolean receiveDmg(int i, Effect[] damages) {
		if (i < 0 || i >= party.length || damages == null) {
			throw new IllegalArgumentException();
		}
		return party[i].receiveDmg(damages);
	}
	
	public boolean recover() {
		for (int i=0; i<party.length; i++) {
			party[i].recover();
		}
		return true;
	}
	
	//get the index of the first member that is alive
	//return -1 if everyone is dead
	public int getMember() {
		for (int i=0; i<party.length; i++) {
			if (!party[i].isDead()) {
				return i;
			}
		}
		return -1;
	}
	
	//return true if everyone in the party is dead, false otherwise
	public boolean allDead() {
		for (int i=0; i<party.length; i++) {
			if (!party[i].isDead()) {
				return false;
			}
		}
		return true;
	}
	
	public int getPartySize() {
		return party.length;
	}
	
	public void printHP() {
		for (int i=0; i<party.length; i++) {
			System.out.print("(" + i + ") ");
			party[i].printHP();
		}
	}
	
	public void printStats() {
		for (int i=0; i<party.length; i++) {
			System.out.print("(" + i + ") ");
			party[i].printCombatStats();
		}
	}
}
