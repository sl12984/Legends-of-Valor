import java.util.Scanner;

public class Battle {
	public final static int HERO = 0;
	public final static int MONSTER = 1;
	private Party heroes;
	private Party monsters;
	private int whosTurn; //which party's turn now
	private Scanner scan;
	
	public Battle(Party heroes, Party monsters) {
		if (heroes == null || monsters == null) {
			throw new IllegalArgumentException();
		}
		this.heroes = heroes;
		this.monsters = monsters;
		whosTurn = 0;
		scan = new Scanner(System.in);
	}
	
	//start the battle and return who won when the battle ends
	public int start() {
		boolean moveSucceeded = false;
		while (true) {
			if (whosTurn == HERO) {
				System.out.println("Heroes: ");
				heroes.printHP();
				System.out.print("Please enter the hero you want to use based on"
						+ " the index at the left (-1 to show stats): ");
				int hero = scan.nextInt();
				while (hero < 0 || hero >= heroes.getPartySize()) {
					if (hero == -1) {
						printStats();
					} else {
						System.out.println("This input is invalid. Please try again.");
					}
					System.out.print("Please enter the hero you want to use based on"
							+ " the index at the left (-1 to show stats): ");
					hero = scan.nextInt();
				}
				
				System.out.print("Please enter your move:\n (0) show stats \n" + 
						" (1) attack \n (2) cast a spell\n (3) use a potion\n "
						+ "(4) equip a weapon or armor");
				int move = scan.nextInt();
				while (move < 1 || move > 4) {
					if (move == 0) {
						printStats();
					} else {
						System.out.println("This input is invalid. Please try again.");
					}
					System.out.print("Please enter your move:\n (0) show stats \n" + 
							" (1) attack \n (2) cast a spell\n (3) use a potion\n "
							+ "(4) equip a weapon or armor");
					move = scan.nextInt();
				}
				
				if (move == 1) {
					Effect[] result = heroes.attack(hero);
					if (result != null) {
						moveSucceeded = true;
						System.out.println("Monsters: ");
						monsters.printHP();
						System.out.print("Please enter the monster you want to attack based on"
								+ " the index at the left (-1 to show stats): ");
						int monster = scan.nextInt();
						while (monster < 0 || monster >= monsters.getPartySize() ||
								!monsters.receiveDmg(monster, result)) {
							if (monster == -1) {
								printStats();
							} else {
								System.out.println("This input is invalid. Please try again.");
							}
							System.out.print("Please enter the monster you want to attack based on"
									+ " the index at the left (-1 to show stats): ");
							monster = scan.nextInt();
						}
						if (monsters.allDead()) {
							return HERO;
						}
					}
				} else if (move == 2) {
					Effect[] result = heroes.castSpell(hero);
					if (result != null) {
						moveSucceeded = true;
						System.out.println("Monsters: ");
						monsters.printHP();
						System.out.print("Please enter the monster you want to cast the "
								+ "spell on based on the index at the left (-1 to show stats): ");
						int monster = scan.nextInt();
						while (monster < 0 || monster >= monsters.getPartySize() || 
								!monsters.receiveDmg(monster, result)) {
							if (monster == -1) {
								printStats();
							} else {
								System.out.println("This input is invalid. Please try again.");
							}
							System.out.print("Please enter the monster you want to cast the "
									+ "spell on based on the index at the left (-1 to show stats): ");
							monster = scan.nextInt();
						}
						if (monsters.allDead()) {
							return HERO;
						}
					}
				} else if (move == 3) {
					moveSucceeded = heroes.usePotion(hero);
				} else {
					moveSucceeded = heroes.equip(hero);
				}
				if (moveSucceeded) {
					whosTurn = MONSTER;
					moveSucceeded = false;
				}
			} else {
				int monster = monsters.getMember();
				Effect[] result = monsters.attack(monster);
				int hero = heroes.getMember();
				heroes.receiveDmg(hero, result);
				if (heroes.allDead()) {
					return MONSTER;
				}
				heroes.recover();
				whosTurn = HERO;
			}
		}
	}
	
	public void printStats() {
		System.out.println("Heroes: ");
		heroes.printStats();
		System.out.println("Monsters: ");
		monsters.printStats();
	}
}
