public class WorldSpace extends Space {
	private Hero hero;
	private int heroID; //whether it's hero 0, 1, or 2 standing in the space, -1 means no hero standing
    private Monster monster;
    private int monsterID;
	public WorldSpace(int n) {
		super(n);
		hero = null;
		heroID = -1;
		monster = null;
		monsterID = -1;
	}
	
    public boolean heroCame(Hero hero, int id){
    	if (this.hero != null) {
    		System.out.println("A hero already occupied this space.");
    		return false;
    	}
        this.hero = hero;
        this.heroID = id;
        return true;
    }

    public boolean monsterCame(Monster monster, int id) {
    	if (this.monster != null) {
    		System.out.println("A monster already occupied this space.");
    		return false;
    	}
        this.monster = monster;
        this.monsterID = id;
        return true;
    }

    public boolean heroLeft() {
        this.hero = null;
        this.heroID = -1;
        return true;
    }

    public boolean monsterLeft() {
        this.monster = null;
        this.monsterID = -1;
        return true;
    }

    public Hero getHero() {
        return hero;
    }

    public Monster getMonster() {
        return monster;
    }
    
    public int getHeroID() {
    	return heroID;
    }
    
    public int getMonsterID() {
    	return monsterID;
    }
    
    public void printLabel() {
		System.out.print("- - - - -");
	}
    
    public void printContent() {
		if (getHeroID() != -1) {
			System.out.print("| H" + getHeroID() + " ");
		} else {
			System.out.print("|    ");
		}
		if (getMonsterID() != -1) {
			System.out.print("M" + getMonsterID() + " |");
		} else {
			System.out.print("   |");
		}
	}
}
