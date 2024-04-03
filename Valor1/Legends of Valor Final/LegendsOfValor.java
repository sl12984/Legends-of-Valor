import java.util.*;

public class LegendsOfValor extends Game {
	public final static int MAX_MONSTER_NUM = 9;
	public final static int HERO = 0;
	public final static int MONSTER = 1;
	
	//stores every possible hero, monster and monster
	private Hero[] heroes;
	private int heroNum;
	private Monster[] monsters;
	private int monsterNum;
	private Item[] items;
	private int itemNum;
	
	private LOVWorld map; //the game map
	private Hero[] myParty; //the heroes player chose to add to the party
	private Monster[] myMonsters; //the monsters spawned
	private int whosTurn; //which party's turn now
	private int roundCount; //the number of rounds passed
	private boolean won; //whether the heroes have won
	private Scanner scan;
	
	public LegendsOfValor() throws Exception {
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
		map = new LOVWorld(8, 8, 1, items, itemNum);
		
		System.out.println("You can choose 3 heroes to join your party. ");
		myParty = new Hero[3];
		
		//print all heroes
		for (int i=0; i<heroNum; i++) {
			System.out.println("(" + i + ") " + heroes[i].getName());
		}
		
		int[] indexes = new int[3]; //used to store the indexes of the heroes you have chosen
		for (int i=0; i<3; i++) {
			indexes[i] = -1;
		}
		
		boolean topOccupied = false;
		boolean midOccupied = false;
		boolean botOccupied = false;
		
		for (int i=0; i<3; i++) {
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
			
			System.out.println("Please which lane you want " + heroes[number].getName()
					+ "to start in: ");
			if (!topOccupied) {
				System.out.println("(0) Top Lane");
			}
			if (!midOccupied) {
				System.out.println("(1) Mid Lane");
			}
			if (!botOccupied) {
				System.out.println("(2) Bot Lane");
			}
		
			int choice = scan.nextInt();
			while (choice < 0 || choice > 2 || map.getSpace(7, choice*3).getHero() != null) {
				System.out.println("This input is invalid. Please try again.");
				System.out.println("Please which lane you want " + heroes[number].getName()
						+ "to start in: ");
				if (!topOccupied) {
					System.out.println("(0) Top Lane");
				}
				if (!midOccupied) {
					System.out.println("(1) Mid Lane");
				}
				if (!botOccupied) {
					System.out.println("(2) Bot Lane");
				}
				choice = scan.nextInt();
			}
			
			if (choice == 0) {
				topOccupied = true;
			}
			if (choice == 1) {
				midOccupied = true;
			}
			if (choice == 2) {
				botOccupied = true;
			}
			
			map.getSpace(7, choice*3).heroCame(myParty[i], i);
			myParty[i].setInitialRow(7);
			myParty[i].setInitialCol(choice*3);
			myParty[i].setRow(7);
			myParty[i].setCol(choice*3);
		}
		
		
		Random rand = new Random();
		myMonsters = new Monster[MAX_MONSTER_NUM];
		
		for (int i=0; i<3; i++) {
			int whichMonster = rand.nextInt(monsterNum);
			while (monsters[whichMonster] == null || monsters[whichMonster].getLevel() != 1) {
				whichMonster = rand.nextInt(monsterNum);
			}
			myMonsters[i] = monsters[whichMonster];
			monsters[whichMonster] = null;
		
			int choice = rand.nextInt(3);
			while (choice < 0 || choice > 2 || map.getSpace(0, choice*3).getMonster() != null) {
				choice = rand.nextInt(3);
			}
			
			map.getSpace(0, choice*3).monsterCame(myMonsters[i], i);
			myMonsters[i].setRow(0);
			myMonsters[i].setCol(choice*3);
		}
		
		whosTurn = HERO;
		roundCount = 0;
		won = false;
	}
	
	public void start() throws Exception {
		while (true) {
			System.out.println("N=Nexus, I=Inaccessible, P=Plain, B=Bush, C=Cave, K=Koulou");
			map.print();
			System.out.println();
			
			if (whosTurn == HERO) {
				for (int index=0; index<myParty.length; index++) {
					if (myParty[index].isDead()) {
						continue;
					}
					
					boolean moveSucceeded = false;
					while (!moveSucceeded) {
						System.out.println("Please choose the action for " + myParty[index].getName() + ":");
						System.out.println("(0) Change Weapon or Armor");
						System.out.println("(1) Use a Potion");
						System.out.println("(2) Attack");
						System.out.println("(3) Cast a Spell");
						System.out.println("(4) Move");
						System.out.println("(5) Teleport");
						System.out.println("(6) Recall");
						System.out.println("(7) Enter Market");
						System.out.println("(8) Show Information");
						System.out.println("(9) Pass");
						System.out.println("(10) Quit");

						int temp = scan.nextInt();
						while(temp < 0 || temp > 10){
							System.out.println("Invalid Input! Please input again!");
							temp = scan.nextInt();
						}
						
						if(temp==0){
							moveSucceeded = myParty[index].equip();
						}else if(temp==1){
							moveSucceeded = myParty[index].usePotion();
						}else if(temp==2){
							moveSucceeded = attack(HERO, index);
						}else if(temp==3){
							moveSucceeded = castSpell(index);
						}else if(temp==4){
							moveSucceeded = heroMove(index);
						}else if(temp==5){
							moveSucceeded = teleport(index);
						}else if(temp==6){
							moveSucceeded = recall(index);
						} else if (temp == 7) {
							if (map.getSpace(myParty[index].getRow(), myParty[index].getCol()) instanceof NexusSpace) {
								enterMarket(myParty[index]);
							} else {
								System.out.println("You are not at a market space.");
							}
						} else if (temp == 8) {
							printPartyStats();
						} else if (temp == 9) {
							moveSucceeded = true;
							System.out.println(myParty[index].getName() + " passed this round.");
						} else {
							end();
							return;
						}
					}
					
				}
				
				for (int i=0; i<8; i++) {
					if (map.getSpace(0, i).getHero() != null) {
						won = true;
						end();
						return;
					}
				}
				
				whosTurn = MONSTER;
				
			} else {
				for (int index=0; index<myMonsters.length; index++) {
					if (myMonsters[index] == null || myMonsters[index].isDead()) {
						continue;
					}
					boolean moveSucceeded = attack(MONSTER, index);
					if (!moveSucceeded) {
						moveSucceeded = monsterMove(index);
					}
					
					if (!moveSucceeded) {
						System.out.println(myMonsters[index].getName() + " passed this round.");
					}
				}
				
				for (int i=0; i<8; i++) {
					if (map.getSpace(7, i).getMonster() != null) {
						end();
						return;
					}
				}
				
				whosTurn = HERO;
				for (int index=0; index<myParty.length; index++) {
					if (myParty[index].isDead()) {
						continue;
					}
					myParty[index].recover();
				}
				
				roundCount++;
				
				if (roundCount%8 == 0) {
					Random rand = new Random();
					for (int i=0; i<3; i++) {
						int whichMonster = rand.nextInt(monsterNum);
						while (monsters[whichMonster] == null || monsters[whichMonster].getLevel() != (roundCount/8)+1) {
							whichMonster = rand.nextInt(monsterNum);
						}
						myMonsters[(roundCount/8)*3 + i] = monsters[whichMonster];
						monsters[whichMonster] = null;
					
						int choice = rand.nextInt(3);
						while (choice < 0 || choice > 2 || map.getSpace(0, choice*3).getMonster() != null) {
							choice = rand.nextInt(3);
						}
						
						map.getSpace(0, choice*3).monsterCame(myMonsters[(roundCount/8)*3 + i], (roundCount/8)*3 + i);
						myMonsters[(roundCount/8)*3 + i].setRow(0);
						myMonsters[(roundCount/8)*3 + i].setCol(choice*3);
					}
				}
			}
			
		}
	}
	
	public void end() throws Exception {
		if (won) {
			System.out.println("Congratulations! You won!");
		} else {
			System.out.println("Unfortunately, you lost.");
		}
	}
	
	public boolean attack(int type, int index) {
		if (type == HERO) {
			int hrow = myParty[index].getRow();
			int hcol = myParty[index].getCol();
			for(int i=-1;i<2;i++){
				for(int j=-1;j<2;j++){
					if(hrow+i>=0 && hrow+i<map.getNumRow() && hcol+j>=0 && hcol+j<map.getNumCol() && map.getSpace(hrow+i,hcol+j).getMonster() != null){
						System.out.println("Monster " + map.getSpace(hrow+i,hcol+j).getMonsterID() + " is in range! Do you want to attack it?(y/n)");
						char temp3 = scan.next().charAt(0);
						while(temp3!='y'&&temp3!='n'){
							System.out.println("You must input y or n!");
							temp3 = scan.next().charAt(0);
						}
						if(temp3=='y'){
							Effect[] result = myParty[index].attack();
							if (result != null) {
								map.getSpace(hrow+i,hcol+j).getMonster().receiveDmg(result);
								if (map.getSpace(hrow+i,hcol+j).getMonster().isDead()) {
									System.out.println("Congratulations! You defeated " + map.getSpace(hrow+i,hcol+j).getMonster().getName() + "!");
									myParty[index].gainExp(Rule.expGain(1));
									myParty[index].gainGold(Rule.goldGain(1));
									map.getSpace(hrow+i,hcol+j).monsterLeft();
								}
								return true;
							}
						}
					}
				}
			}
			return false;
		} else {
			int mrow = myMonsters[index].getRow();
			int mcol = myMonsters[index].getCol();
			for(int i=-1;i<2;i++){
				for(int j=-1;j<2;j++){
					if(mrow+i>=0 && mrow+i<map.getNumRow() && mcol+j>=0 && mcol+j<map.getNumCol() && map.getSpace(mrow+i,mcol+j).getHero() != null){
						Effect[] result = myMonsters[index].attack();
						if (result != null) {
							map.getSpace(mrow+i,mcol+j).getHero().receiveDmg(result);
							if (map.getSpace(mrow+i,mcol+j).getHero().isDead()) {
								System.out.println(map.getSpace(mrow+i,mcol+j).getHero().getName() + " is defeated!");
								if (!recall(map.getSpace(mrow+i,mcol+j).getHeroID())) {
									System.out.println(map.getSpace(mrow+i,mcol+j).getHero().getName() + " is unable to respawn.");
									map.getSpace(mrow+i,mcol+j).heroLeft();
								}
							}
							return true;
						}
					}
				}
			}
			return false;
		}
	}
	
	public boolean castSpell(int index) {
		int hrow = myParty[index].getRow();
		int hcol = myParty[index].getCol();
		for(int i=-1;i<2;i++){
			for(int j=-1;j<2;j++){
				if(hrow+i>=0 && hrow+i<map.getNumRow() && hcol+j>=0 && hcol+j<map.getNumCol() && map.getSpace(hrow+i,hcol+j).getMonster() != null){
					System.out.println("Monster " + map.getSpace(hrow+i,hcol+j).getMonsterID() + " is in range! Do you want to attack it?(y/n)");
					char temp3 = scan.next().charAt(0);
					while(temp3!='y'&&temp3!='n'){
						System.out.println("You must input y or n!");
						temp3 = scan.next().charAt(0);
					}
					if(temp3=='y'){
						Effect[] result = myParty[index].castSpell();
						if (result != null) {
							map.getSpace(hrow+i,hcol+j).getMonster().receiveDmg(result);
							if (map.getSpace(hrow+i,hcol+j).getMonster().isDead()) {
								System.out.println("Congratulations! You defeated " + map.getSpace(hrow+i,hcol+j).getMonster().getName() + "!");
								myParty[index].gainExp(Rule.expGain(1));
								myParty[index].gainGold(Rule.goldGain(1));
								map.getSpace(hrow+i,hcol+j).monsterLeft();
							}
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	public void enterMarket(Hero hero) {
		int currentRow = hero.getRow();
		int currentCol = hero.getCol();
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
			
			Item toSell = hero.sell();
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
			
			boolean success = hero.buy(item);
			if (success) {
				currentSpace.getMarket().removeItem(i);
			}
		}

	}
	
	public boolean heroMove(int index) {
		int row = myParty[index].getRow();
		int col = myParty[index].getCol();
		while (true) {
			System.out.println("Please choose where you want to go:");
			System.out.println("• W/w: move up\n" +
					"• A/a: move left\n" +
					"• S/s: move down\n" +
					"• D/d: move right" + 
					"• R/r: return to the previous menu (I don't want to move anymore)");

			String dir = scan.next();

			if (dir.equalsIgnoreCase("w")) {
				// out of range or invalid
				// a hero cannot move behind a monster without killing it
				// a hero cannot move into a space already occupied by another hero
				if (row - 1 < 0 || map.getSpace(row - 1, col) instanceof InaccessibleSpace ||
						map.getSpace(row - 1,col).getHero() != null ||
						!canMoveForward(myParty[index])) {
					System.out.println("Sorry that's an invalid move");
					continue;
				}
				map.getSpace(row, col).heroLeft();
				row -= 1;
				break;
			}
			else if (dir.equalsIgnoreCase("a")) {
				// out of range or invalid
				if (col - 1 < 0 || map.getSpace(row, col-1) instanceof InaccessibleSpace ||
						map.getSpace(row, col-1).getHero() != null) {
					System.out.println("Sorry that's an invalid move");
					continue;
				}
				map.getSpace(row, col).heroLeft();
				col -= 1;
				break;
			}
			else if (dir.equalsIgnoreCase("s")) {
				// out of range or invalid
				if (row + 1 >= 8 || map.getSpace(row+1, col) instanceof InaccessibleSpace ||
						map.getSpace(row+1, col).getHero() != null) {
					System.out.println("Sorry that's an invalid move");
					continue;
				}
				map.getSpace(row, col).heroLeft();
				row += 1;
				break;
			}
			else if (dir.equalsIgnoreCase("d")) {
				// out of range or invalid
				if (col + 1 >= 8 || map.getSpace(row, col+1) instanceof InaccessibleSpace ||
						map.getSpace(row, col+1).getHero() != null) {
					System.out.println("Sorry that's an invalid move");
					continue;
				}
				map.getSpace(row, col).heroLeft();
				col += 1;
				break;
			} else if (dir.equalsIgnoreCase("r")) {
				return false;
			}
			else {
				System.out.println("Sorry that's an invalid move");
			}
		}

		// let hero move to this cell
		myParty[index].setRow(row);
		myParty[index].setCol(col);
		map.getSpace(row, col).heroCame(myParty[index], index);
		System.out.println(myParty[index].getName() + " has moved to row " + row + ", col " + col);
		map.print();
		return true;
	}

	// check if hero can move forward
	public boolean canMoveForward(Hero h) {
		int row = h.getRow();
		int col = h.getCol();
		if (map.getSpace(row, col).getMonster() != null) return false;
		if (col % 3 == 0) {
			if (map.getSpace(row, col+1).getMonster() != null) return false;
		}
		else if (map.getSpace(row, col-1).getMonster() != null){
			return false;
		}
		return true;
	}

	public boolean monsterMove(int index) {
		int row = myMonsters[index].getRow();
		int col = myMonsters[index].getCol();
		
		if (map.getSpace(row, col).getHero() != null) return false;
		if (col % 3 == 0) {
			if (map.getSpace(row, col+1).getHero() != null) return false;
		}
		else if (map.getSpace(row, col-1).getHero() != null){
			return false;
		}

		if (row < 7) {
			map.getSpace(row,col).monsterLeft();
			myMonsters[index].setRow(row + 1);
			map.getSpace(row+1, col).monsterCame(myMonsters[index], index);
			System.out.println(myMonsters[index].getName() + " has moved to row " + (row + 1) + ", col " + col);
			map.print();
			return true;
		}
		return false;
	}

	public boolean teleport(int index) {
		int row = myParty[index].getRow();
		int col = myParty[index].getCol();
		int targetRow = row;
		int targetCol = col;
		while (true) {
			System.out.println("Please choose the target hero row (Enter R to return to the previous menu)");
			String temp = scan.next();
			if (temp.equalsIgnoreCase("R")) {
				return false;
			}
			while(temp.length() != 1||temp.charAt(0) < 48||temp.charAt(0) > 57){
				System.out.println("Invalid Input! Please input again!");
				temp = scan.next();
			}
			targetRow = Integer.parseInt(temp);

			System.out.println("Please choose the target hero col");
			temp = scan.next();
			while(temp.length() != 1||temp.charAt(0) < 48||temp.charAt(0) > 57){
				System.out.println("Invalid Input! Please input again!");
				temp = scan.next();
			}
			targetCol = Integer.parseInt(temp);

			// no target hero
			if (map.getSpace(targetRow,targetCol).getHero() == null) {
				System.out.println("Target hero doesn't exist");
				continue;
			}

			// not at different lane
			if (Math.abs(targetCol - col) < 2) {
				System.out.println("Invalid. Not at different lane");
				continue;
			}

			System.out.println("Do you want to move next or behind the target hero? (next / behind)");
			String choice = scan.next();
			if (choice.equalsIgnoreCase("next")) {
				if (targetCol % 3 == 0) {
					targetCol++;
				}
				else {
					targetCol--;
				}
				if (map.getSpace(targetRow,targetCol).getHero() != null) {
					System.out.println("Invalid move. There's already a hero");
					continue;
				}
				break;
			}
			else if (choice.equalsIgnoreCase("behind")) {
				if (targetRow == 7) {
					System.out.println("Invalid move");
					continue;
				}
				targetRow++;
				if (map.getSpace(targetRow,targetCol).getHero() != null) {
					System.out.println("Invalid move. There's already a monster");
					continue;
				}
				break;
			}
			else {
				System.out.println("Invalid move");
			}
		}
		// let hero move to this cell
		map.getSpace(row,col).heroLeft();
		myParty[index].setRow(targetRow);
		myParty[index].setCol(targetCol);
		map.getSpace(targetRow,targetCol).heroCame(myParty[index], index);
		System.out.println(myParty[index].getName() + " has teleported to row " + targetRow + ", col " + targetCol);
		map.print();
		return true;
	}

	public boolean recall(int index) {
		// let hero move to this cell
		int row = myParty[index].getRow();
		int col = myParty[index].getCol();
		int targetRow = myParty[index].getInitialRow();
		int targetCol = myParty[index].getInitialCol();
		if (map.getSpace(targetRow,targetCol).getHero() != null) {
			System.out.println("Invalid move");
			return false;
		}

		map.getSpace(row,col).heroLeft();
		myParty[index].setRow(targetRow);
		myParty[index].setCol(targetCol);
		map.getSpace(targetRow,targetCol).heroCame(myParty[index], index);
		System.out.println(myParty[index].getName() + " has recalled to row " + targetRow + ", col " + targetCol);
		map.print();
		return true;
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
