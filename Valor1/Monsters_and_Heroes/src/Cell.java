public class Cell {
    private HeroParty h;
    private MonsterParty m;
    public Cell() {
        this.h = null;
        this.m = null;
    }

    // hero comes
    public void heroCome(HeroParty h){
        this.h = h;
    }

    // monster comes
    public void monsterCome(MonsterParty m) {
        this.m = m;
    }

    // hero leaves
    public void heroLeft() {
        this.h = null;
    }

    // monster leaves
    public void monsterLeft() {
        this.m = null;
    }

    public HeroParty getH() {
        return h;
    }

    public MonsterParty getM() {
        return m;
    }

    public void SetH(HeroParty h){
        this.h = h;
    }

    @Override
    public String toString(){
        return "   ";
    }
}
