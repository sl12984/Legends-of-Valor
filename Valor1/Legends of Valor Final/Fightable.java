public interface Fightable {
	//return the effects that will be done to the opponent
	public Effect[] attack();
	
	//return the effects that will be done to the opponent
	public Effect[] castSpell();
	
	//return true if successfully used a potion, false otherwise
	public boolean usePotion();
	
	//return true if successfully used a equipped something, false otherwise
	public boolean equip();
	
	//return true if successfully received the damages, false otherwise
	public boolean receiveDmg(Effect[] damages);
	
	//recover a portion of stats
	public boolean recover();
	
	//return true if this fightable is dead, false otherwise
	public boolean isDead();
	
	//revive the fightable and reset all attributes to those before the fight
	public void reset();
	
	public void printHP();
	
	public void printCombatStats();
}
