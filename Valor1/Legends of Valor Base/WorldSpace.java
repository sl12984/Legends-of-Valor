public class WorldSpace extends Space {
	private Hero hero;
    private Monster monster;
	public WorldSpace(int n) {
		super(n);
		hero = null;
		monster = null;
	}
	
    public boolean heroCame(Hero hero){
    	if (this.hero != null) {
    		System.out.println("A hero already occupied this space.");
    		return false;
    	}
        this.hero = hero;
        return true;
    }

    public boolean monsterCame(Monster monster) {
    	if (this.monster != null) {
    		System.out.println("A monster already occupied this space.");
    		return false;
    	}
        this.monster = monster;
        return true;
    }

    public boolean heroLeft() {
        this.hero = null;
        return true;
    }

    public boolean monsterLeft() {
        this.monster = null;
        return true;
    }

    public Hero getHero() {
        return hero;
    }

    public Monster getMonster() {
        return monster;
    }
}
