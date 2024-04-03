import java.util.Scanner;

public class Play {
	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		
		try {
			System.out.println("Welcome!");
			
			while (true) {
				System.out.println();
				System.out.println("What would you like to play?");
				System.out.println("(1) Monsters and Heroes");
				System.out.println("(2) Legends of Valor");
				System.out.println("(0) Exit");
				int choice = scan.nextInt();
	
				Game game = null;
				if (choice == 1) {
					game = new MonstersAndHeroes();
				} else if (choice == 2) {
					game = new LegendsOfValor();
				} else {
					System.out.println("Thank you for playing. Goodbye!");
					break;
				}
				
				game.start();
				System.out.print("Do you want to play again? (y/n)");
				char answer = scan.next().charAt(0);
				if (answer != 'y') {
					System.out.println("Thank you for playing. Goodbye!");
					break;
				}
			}
			
		} catch (Exception e) {
			System.out.println("You have entered an invalid input. Game Terminated. " + e);
		}

		scan.close();
	}
}
