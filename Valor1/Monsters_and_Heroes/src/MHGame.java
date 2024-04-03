import java.util.Scanner;

public class MHGame {
    public void run(){
        Board board = gameBoard();
        System.out.println("Please Enter Number of Heroes from 1 to 3:");
        Scanner sc = new Scanner(System.in);
        String temp = sc.next();
        while(temp.length()!=1 || temp.charAt(0) < 49 || temp.charAt(0) > 51){
            System.out.println("The number should be between 1 and 3! Please Input Again");
            temp = sc.next();
        }
        int num =temp.charAt(0)-48;
        // Set the initial location of heroes
        HeroParty h = new HeroParty(num);
        board.getBoard()[1][2].SetH(h);
        // Use two variables to store the location of heroes
        int heroRow = 1;
        int heroCol = 2;
        System.out.println("Your Heroes are:");
        for(int i=0;i<num;i++){
            System.out.println(h.getHeroParty().get(i).getType()+" "+h.getHeroParty().get(i).getName());
        }
        board.print();
        System.out.println("Instructions: W means weapon market, A means armor market.");
        System.out.println("P means potion market, S means spell market.");
        System.out.println("X means inaccessible places, H means hero.");
        System.out.println("The value of heroes and monsters are as follows:");
        System.out.println(new HeroParty().toString());
        System.out.println(new MonsterParty().toString());

        boolean gameEnd = false;
        while(!gameEnd) {
            System.out.println("You should press key to make actions.");
            System.out.println("W/w, A/a, S/s, D/d for moving up, left, down and right, Q/q for Quit, I/i for information");
            System.out.println("Each time you enter a market grid, you can press m to buy an item");
            System.out.println("Please enter a key for your action:");
            temp = sc.next();
            char c = temp.charAt(0);
            while (temp.length()!=1||(c!='W'&&c!='w'&&c!='A'&&c!='a'&&c!='S'&&c!='s'&&c!='D'&&c!='d'&&c!='Q'&&c!='q'&&c!='I'&&c!='i')){
                System.out.println("Invalid Input! Please Enter the Key Again!");
                temp = sc.next();
                c = temp.charAt(0);
            }
            // Move in each direction
            if(temp.charAt(0)=='W'||temp.charAt(0)=='w'){
                if(heroRow == 0 || board.getBoard()[heroRow-1][heroCol].toString().equals("X  ")){
                    continue;
                }else{
                    board.getBoard()[heroRow][heroCol].heroLeft();
                    board.getBoard()[heroRow-1][heroCol].heroCome(h);
                    heroRow -= 1;
                }
            }else if(temp.charAt(0)=='A'||temp.charAt(0)=='a'){
                if(heroCol == 0 || board.getBoard()[heroRow][heroCol-1].toString().equals("X  ")){
                    continue;
                }else{
                    board.getBoard()[heroRow][heroCol].heroLeft();
                    board.getBoard()[heroRow][heroCol-1].heroCome(h);
                    heroCol -= 1;
                }
            }else if(temp.charAt(0)=='S'||temp.charAt(0)=='s'){
                if(heroRow == 7 || board.getBoard()[heroRow+1][heroCol].toString().equals("X  ")){
                    continue;
                }else{
                    board.getBoard()[heroRow][heroCol].heroLeft();
                    board.getBoard()[heroRow+1][heroCol].heroCome(h);
                    heroRow += 1;
                }
            }else if(temp.charAt(0)=='D'||temp.charAt(0)=='d'){
                if(heroCol == 7 || board.getBoard()[heroRow][heroCol+1].toString().equals("X  ")){
                    continue;
                }else{
                    board.getBoard()[heroRow][heroCol].heroLeft();
                    board.getBoard()[heroRow][heroCol+1].heroCome(h);
                    heroCol += 1;
                }
            }else if(temp.charAt(0)=='Q'||temp.charAt(0)=='q'){
                System.out.println("Game Quited!");
                System.exit(0);
            }else if(temp.charAt(0)=='I'||temp.charAt(0)=='i'){
                // Display information of heroes
                display(h);
            }
            // Check whether game ends
            for(int i=0;i<h.size();i++){
                if(h.getHeroParty().get(i).getHP()>0){
                    break;
                }else if(i==h.size()-1){
                    gameEnd = true;
                    break;
                }
            }

        }

    }
    public Board gameBoard(){
        Board board = new Board(8,8);
        board.setCell(0,0,new WeaponMarketCell());
        board.setCell(0,1,new WeaponMarketCell());
        board.setCell(1,0,new WeaponMarketCell());
        board.setCell(1,1,new WeaponMarketCell());
        board.setCell(0,6,new ArmorMarketCell());
        board.setCell(0,7,new ArmorMarketCell());
        board.setCell(1,6,new ArmorMarketCell());
        board.setCell(1,7,new ArmorMarketCell());
        board.setCell(6,0,new PotionMarketCell());
        board.setCell(6,1,new PotionMarketCell());
        board.setCell(7,0,new PotionMarketCell());
        board.setCell(7,1,new PotionMarketCell());
        board.setCell(6,6,new SpellMarketCell());
        board.setCell(6,7,new SpellMarketCell());
        board.setCell(7,6,new SpellMarketCell());
        board.setCell(7,7,new SpellMarketCell());
        board.setCell(3,0,new InaccessibleCell());
        board.setCell(3,5,new InaccessibleCell());
        board.setCell(4,2,new InaccessibleCell());
        board.setCell(0,4,new InaccessibleCell());
        board.setCell(2,3,new InaccessibleCell());
        board.setCell(5,4,new InaccessibleCell());
        board.setCell(7,3,new InaccessibleCell());
        board.setCell(4,1,new InaccessibleCell());

        return board;
    }

    public void display(HeroParty h){
        System.out.println("Here's your current heroes info");
        String str = Formatter.space("Number", 10)
                + Formatter.space("Type", 12)
                + Formatter.space("Name", 20)
                + Formatter.space("Level", 10)
                + Formatter.space("HP", 10)
                + Formatter.space("MP", 10)
                + Formatter.space("Strength", 10)
                + Formatter.space("Dexterity", 10)
                + Formatter.space("Agility", 10)
                + Formatter.space("Gold", 10)
                + Formatter.space("Experience", 10) + "\n";

        int number = 1;
        for (Hero hero: h.getHeroParty()) {
            str += Formatter.space(Integer.toString(number), 10) + h.toString() + "\n";
            number++;
        }
        System.out.println(str);
    }

    public void display(MonsterParty m){
        System.out.println("Here's your current monsters info");
        String str = Formatter.space("Type", 12)
                + Formatter.space("Name", 20)
                + Formatter.space("Level", 10)
                + Formatter.space("HP", 10)
                + Formatter.space("Damage", 10)
                + Formatter.space("Defense", 10)
                + Formatter.space("Dodge", 10)
                + "\n";
        for (Monster monster: m.getMonsterParty()) {
            str += monster.toString() + "\n";
        }
        System.out.println(str);
    }
}
