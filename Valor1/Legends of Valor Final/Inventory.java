public class Inventory {
	public final static int MAX_SIZE = 100;
	private Item[] inventory;
	private int num; //number of items in inventory
	
	//takes in the capacity of the inventory
	public Inventory(int size) {
		if (size < 0 || size > MAX_SIZE) {
			throw new IllegalArgumentException("Invalid size.");
		}
		inventory = new Item[size];
		num = 0;
	}
	
	//add item to the inventory, return true if successfully added, false otherwise
	public boolean addItem(Item item) {
		if (item == null) {
			return true;
		}
		if (num == inventory.length) {
			//inventory is full
			return false;
		} else {
			//inventory not full
			for (int i=0; i<inventory.length; i++) {
				//find an empty slot to place item
				if (inventory[i] == null) {
					inventory[i] = item;
					num++;
					return true;
				}
			} 
		}
		return false;
	}
	
	//remove item at index i from the inventory, return true if successfully removed, false otherwise
	public boolean removeItem(int i) {
		if (i < 0 || i >= inventory.length) {
			return true;
		}
		inventory[i] = null;
		num--;
		return true;
	}
	
	//get item at index i
	public Item getItem(int i) {
		if (i < 0 || i >= inventory.length) {
			return null;
		}
		return inventory[i];
	}
	
	public int getNum() {
		return num;
	}
	
	public void print() {
		for (int i=0; i<inventory.length; i++) {
			if (inventory[i] != null) {
				System.out.print("(" + i + ") ");
				inventory[i].print();
			}
		}
	}
	
	public void printWeaponArmor() {
		System.out.println("Weapons and Armors in inventory: ");
		for (int i=0; i<inventory.length; i++) {
			if (inventory[i] instanceof Weapon || inventory[i] instanceof Armor) {
				System.out.print("(" + i + ") ");
				inventory[i].print();
			}
		}
	}
	
	public void printSpell() {
		System.out.println("Spells in inventory: ");
		for (int i=0; i<inventory.length; i++) {
			if (inventory[i] instanceof Spell) {
				System.out.print("(" + i + ") ");
				inventory[i].print();
			}
		}
	}
	
	public void printPotion() {
		System.out.println("Potions in inventory: ");
		for (int i=0; i<inventory.length; i++) {
			if (inventory[i] instanceof Potion) {
				System.out.print("(" + i + ") ");
				inventory[i].print();
			}
		}
	}
}
