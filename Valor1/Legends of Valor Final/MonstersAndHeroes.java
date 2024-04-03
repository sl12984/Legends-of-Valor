import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MonstersAndHeroes extends Game {
	//stores every possible hero, monster and monster
	private Hero[] heroes;
	private int heroNum;
	private Monster[] monsters;
	private int monsterNum;
	private Item[] items;
	private int itemNum;
	
	private MAHWorld map; //the game map
	private Hero[] myParty; //the heroes player chose to add to the party
	private int currentRow; //the current location of the player
	private int currentCol;
	private Scanner scan;
	
	public MonstersAndHeroes() throws Exception {
		scan = new Scanner(System.in);
		
		heroes = new Hero[100];
		monsters = new Monster[100];
		items = new Item[1000];
		
		int index = 0;
		//reading heroes
		ArrayList<Paladin> paladins = Factory.createPaladins("Paladins.txt");
		ArrayList<Sorcerer> sorcerers = Factory.createSorcerers("Sorcerers.txt");
		ArrayList<Warrior> warriors = Factory.createWarriors("Warriors.txt");
		for (int i=0; i<paladins.size(); i++) {
			heroes[index] = paladins.get(i);
			index++;
		}
		for (int i=0; i<sorcerers.size(); i++) {
			heroes[index] = sorcerers.get(i);
			index++;
		}
		for (int i=0; i<warriors.size(); i++) {
			heroes[index] = warriors.get(i);
			index++;
		}
		heroNum = index;
				
		//reading monsters
		ArrayList<Dragon> dragons = Factory.createDragons("Dragons.txt");
		ArrayList<Exoskeleton> exoskeletons = Factory.createExoskeletons("Exoskeletons.txt");
		ArrayList<Spirit> spirits = Factory.createSpirits("Spirits.txt");
		index = 0;
		for (int i=0; i<dragons.size(); i++) {
			monsters[index] = dragons.get(i);
			index++;
		}
		for (int i=0; i<exoskeletons.size(); i++) {
			monsters[index] = exoskeletons.get(i);
			index++;
		}
		for (int i=0; i<spirits.size(); i++) {
			monsters[index] = spirits.get(i);
			index++;
		}
		monsterNum = index;
				
		index = 0;
		//reading items
		ArrayList<Armor> armors = Factory.createArmors("Armory.txt");
		ArrayList<FireSpell> fireSpells = Factory.createFireSpells("FireSpells.txt");
		ArrayList<IceSpell> iceSpells = Factory.createIceSpells("IceSpells.txt");
		ArrayList<LightningSpell> lightningSpells = Factory.createLightningSpells("LightningSpells.txt");
		ArrayList<Potion> potions = Factory.createPotions("Potions.txt");
		ArrayList<Weapon> weapons = Factory.createWeapons("Weaponry.txt");
		index = 0;
		for (int i=0; i<armors.size(); i++) {
			items[index] = armors.get(i);
			index++;
		}
		for (int i=0; i<fireSpells.size(); i++) {
			items[index] = fireSpells.get(i);
			index++;
		}
		for (int i=0; i<iceSpells.size(); i++) {
			items[index] = iceSpells.get(i);
			index++;
		}
		for (int i=0; i<lightningSpells.size(); i++) {
			items[index] = lightningSpells.get(i);
			index++;
		}
		for (int i=0; i<potions.size(); i++) {
			items[index] = potions.get(i);
			index++;
		}
		for (int i=0; i<weapons.size(); i++) {
			items[index] = weapons.get(i);
			index++;
		}
		itemNum = index;
		
		
		//setting up game
		System.out.print("Please enter the size of your map (>=4 and <=9): ");
		int width = scan.nextInt();
		while (width < 4 || width > 9) {
			System.out.println("This input is invalid. Please try again.");
			System.out.print("Please enter the size of your map (>=4 and <=9): ");
			width = scan.nextInt();
		}
		
		map = new MAHWorld(width, width, 1, items, itemNum);
		currentRow = 0;
		currentCol = 0;
		
		//put hero at (0,0)
		map.setContentAt(currentRow, currentCol, 1);
		
		System.out.print("You can choose 1-3 heroes to join your party. ");
		System.out.print("Please enter how many heroes you want to choose (>=1 and <=3): ");
		int num = scan.nextInt();
		while (num < 1 || num > 3) {
			System.out.println("This input is invalid. Please try again.");
			System.out.print("Please enter how many heroes you want to choose (>=1 and <=3): ");
			num = scan.nextInt();
		}
		
		myParty = new Hero[num];
		
		//print all heroes
		for (int i=0; i<heroNum; i++) {
			System.out.println("(" + i + ") " + heroes[i].getName());
		}
		
		int[] indexes = new int[3]; //used to store the indexes of the heroes you have chosen
		for (int i=0; i<3; i++) {
			indexes[i] = -1;
		}
		
		for (int i=0; i<num; i++) {
			System.out.print("Please enter the index of the hero you want to choose "
					+ "(you cannot choose duplicate heroes): ");
			int number = scan.nextInt();
			while (number < 0 || number >= heroNum || number == indexes[0] 
					|| number == indexes[1] || number == indexes[2]) {
				System.out.println("This input is invalid. Please try again.");
				System.out.print("Please enter the index of the hero you want to choose "
						+ "(you cannot choose duplicate heroes): ");
				number = scan.nextInt();
			}
			System.out.println("You chose " + heroes[number].getName() + ".");
			myParty[i] = heroes[number];
			indexes[i] = number;
		}
		
		printControls();
	}
	
	public void start() throws Exception {
		while (true) {
			System.out.println("O=where you are, X=inaccessible, M=market");
			map.print();
			if (map.getSpace(currentRow, currentCol) instanceof NexusSpace) {
				System.out.println("You are at a market space.");
			} else {
				System.out.println("You are not at a market space.");
			}
			System.out.print("Please enter your move: ");
			char choice = scan.next().charAt(0);
			
			boolean moved = false;

			if (choice == 'W' || choice == 'w') {
				//move up
				if (currentRow == 0) {
					System.out.println("Invalid move.");
					continue;
				}
				if (map.setContentAt(currentRow-1, currentCol, 1)) {
					map.setContentAt(currentRow, currentCol, 0);
					currentRow--;
					moved = true;
				} else {
					System.out.println("Invalid move.");
				}
			} else if (choice == 'A' || choice == 'a') {
				//move left
				if (currentCol == 0) {
					System.out.println("Invalid move.");
					continue;
				}
				if (map.setContentAt(currentRow, currentCol-1, 1)) {
					map.setContentAt(currentRow, currentCol, 0);
					currentCol--;
					moved = true;
				} else {
					System.out.println("Invalid move.");
				}
			} else if (choice == 'S' || choice == 's') {
				//move down
				if (currentRow == map.getNumRow()-1) {
					System.out.println("Invalid move.");
					continue;
				}
				if (map.setContentAt(currentRow+1, currentCol, 1)) {
					map.setContentAt(currentRow, currentCol, 0);
					currentRow++;
					moved = true;
				} else {
					System.out.println("Invalid move.");
				}
			} else if (choice == 'D' || choice == 'd') {
				//move right
				if (currentCol == map.getNumCol()-1) {
					System.out.println("Invalid move.");
					continue;
				}
				if (map.setContentAt(currentRow, currentCol+1, 1)) {
					map.setContentAt(currentRow, currentCol, 0);
					currentCol++;
					moved = true;
				} else {
					System.out.println("Invalid move.");
				}
			} else if (choice == 'Q' || choice == 'q') {
				//quit
				end();
				break;
			} else if (choice == 'I' || choice == 'i') {
				//show stats
				printPartyStats();
			} else if (choice == 'M' || choice == 'm') {
				//enter market
				if (map.getSpace(currentRow, currentCol) instanceof NexusSpace) {
					enterMarket();
				} else {
					System.out.println("You are not at a market space.");
				}
			} else if (choice == 'C' || choice == 'c') {
				//show controls
				printControls();
			} else {
				System.out.println("Invalid input.");
			}
			
			if (moved && map.getSpace(currentRow, currentCol) instanceof PlainSpace) {
				//battle or not
				Random rand = new Random();
				int enterBattle = rand.nextInt(2);
				if (enterBattle == 1) {
					//enter battle
					System.out.println("Some monsters blocked your way. Battle starts.");
					if (!enterBattle()) {
						//game over
						System.out.println("All your heroes have fainted. Game over!");
						end();
						break;
					}
				}
				moved = false;
			}
		}
	};
	
	public void end() throws Exception {
		System.out.println("You have exited the game.");
	};
	
	public void printControls() {
		System.out.println("Controls:");
		System.out.println("W/w: move up");
		System.out.println("A/a: move left");
		System.out.println("S/s: move down");
		System.out.println("D/d: move right");
		System.out.println("Q/q: quit game");
		System.out.println("I/i: show information");
		System.out.println("M/m: enter market");
		System.out.println("C/c: show controls");
	}
	
	//return false if gameover, true if hero won
	public boolean enterBattle() {
		Random rand = new Random();
		Party myHeroes = new Party(myParty);
		
		//get highest level in hero party
		int highestLevel = 0;
		for (int i=0; i<myParty.length; i++) {
			if (myParty[i].getLevel()> highestLevel) {
				highestLevel = myParty[i].getLevel();
			}
		}
	
		Monster[] opponent = new Monster[myParty.length];
		int[] indexes = new int[3]; //used to store indexes of the monsters chosen
		for (int i=0; i<3; i++) {
			indexes[i] = -1;
		}
		for (int i=0; i<opponent.length; i++) {
			//randomly choose monsters with at most level "highestLevel"
			int whichMonster = rand.nextInt(monsterNum);
			while (whichMonster == indexes[0] || whichMonster == indexes[1] || 
					whichMonster == indexes[2] || monsters[whichMonster].getLevel() > highestLevel) {
				whichMonster = rand.nextInt(monsterNum);
			}
			opponent[i] = monsters[whichMonster];
			indexes[i] = whichMonster;
		}
		
		Party myMonsters = new Party(opponent);
		Battle battle = new Battle(myHeroes, myMonsters);
		int whoWon = battle.start();
		if (whoWon == Battle.HERO) {
			System.out.println("Congratulations! You won the battle!");
			for (int i=0; i<myParty.length; i++) {
				myParty[i].gainExp(highestLevel * 10);
				myParty[i].gainGold(highestLevel * 100);
				myParty[i].reset();
			}
			for (int i=0; i<opponent.length; i++) {
				opponent[i].reset();
			}
			return true;
		} else {
			return false;
		}
	}
	
	public void enterMarket() {
		NexusSpace currentSpace = (NexusSpace)map.getSpace(currentRow, currentCol);
		
		currentSpace.getMarket().print();
		System.out.println();
		printPartyInventory();
		System.out.print("Welcome to the market. Do you want to (1) sell or (2) buy? ");
		int choice = scan.nextInt();
		while (choice != 1 && choice != 2) {
			System.out.println("This input is invalid. Please try again.");
			System.out.print("Do you want to (1) sell or (2) buy? ");
			choice = scan.nextInt();
		}
		
		if (choice == 1) {
			//sell
			if (currentSpace.getMarket().isFull()) {
				System.out.println("This market is full. No more item can be sold at this market.");
				return;
			}
			
			printPartyName();
			System.out.print("Which hero's item do you want to sell?"
					+ " (Enter the index on the left (-1 to show all the inventories)) ");
			int hero = scan.nextInt();
			while (hero < 0 || hero >= heroes.length) {
				if (hero == -1) {
					printPartyInventory();
				} else {
					System.out.println("This input is invalid. Please try again.");
				}
				System.out.print("Which hero's item do you want to sell?"
						+ " (Enter the index on the left (-1 to show all the inventories)) ");
				hero = scan.nextInt();
			}
			
			Item toSell = myParty[hero].sell();
			currentSpace.getMarket().addItem(toSell);
		} else {
			//buy
			currentSpace.getMarket().print();
			System.out.print("Please enter the item you want to buy based on the index "
					+ "on the left: ");
			int i = scan.nextInt();
			Item item = currentSpace.getMarket().getItem(i);
			while (item == null) {
				System.out.println("This input is invalid. Please try again.");
				System.out.print("Please enter the item you want to buy based on the index "
						+ "on the left: ");
				i = scan.nextInt();
				item = currentSpace.getMarket().getItem(i);
			}
			
			printPartyName();
			System.out.print("Which hero do you want to buy this item for?"
					+ " (Enter the index on the left (-1 to show all the inventories)) ");
			int hero = scan.nextInt();
			while (hero < 0 || hero >= myParty.length) {
				if (hero == -1) {
					printPartyInventory();
				} else {
					System.out.println("This input is invalid. Please try again.");
				}
				System.out.print("Which hero do you want to buy this item for?"
						+ " (Enter the index on the left (-1 to show all the inventories)) ");
				hero = scan.nextInt();
			}
			
			boolean success = myParty[hero].buy(item);
			if (success) {
				currentSpace.getMarket().removeItem(i);
			}
		}
	}
	
	public void printPartyName() {
		for (int i=0; i < myParty.length; i++) {
			System.out.println("(" + i + ") " + myParty[i].getName());
		}
	}
	
	public void printPartyInventory() {
		for (int i=0; i < myParty.length; i++) {
			myParty[i].printInventory();
			System.out.println();
		}
	}
	
	public void printPartyStats() {
		for (int i=0; i < myParty.length; i++) {
			myParty[i].printStats();
			System.out.println();
		}
	}
}
