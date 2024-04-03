public class NormalCell extends Cell{
    @Override
    public void heroCome(HeroParty h){
        super.heroCome(h);
        // Throw a dice to decide whether heroes will meet monsters
        int dice = Dice.rowDice();
        if(dice > 3){
            System.out.println("Heroes meet monsters!");
            MonsterParty m = new MonsterParty(h.getHeroParty().size(),h.getHeroParty().get(0).getLevel());
            System.out.println(m.toString());
            this.monsterCome(m);
            Fight fight = new Fight(h, m);
            while (FightEnd.isEnd(h, m) == null) {
                fight.run();
            }
            if (FightEnd.isEnd(h, m) == "h") {
                System.out.println("Hero won!");
                this.monsterLeft();
                // As we assume the first hero is always first attacked, all heroes get gold
                for(int i=0;i<h.size();i++){

                    // Revive dead heroes
                    if(h.getHeroParty().get(i).getHP()<=0){
                        h.getHeroParty().get(i).setHP(h.getHeroParty().get(i).getLevel() * 50);
                        h.getHeroParty().get(i).setMP(h.getHeroParty().get(i).getMP() / 2);
                    }
                    h.getHeroParty().get(i).setGold(h.getHeroParty().get(i).getGold() + m.getMonsterParty().get(0).getLevel()*100);
                    h.getHeroParty().get(i).gainExp(m.size()*2);
                }
            }
            else {
                System.out.println("Hero lost! You Lose!");
                // Game ends
                System.exit(0);
            }
        }


    }

    @Override
    public String toString(){
        if(this.getH()!=null){
            return "H  ";
        }else{
            return "   ";
        }
    }
}
