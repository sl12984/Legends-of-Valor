public class FightEnd {
    public static String isEnd(HeroParty h, MonsterParty m) {
        int count = 0;
        for (Hero hero: h.getHeroParty()) {
            if (hero.getHP() <= 0) count++;
        }
        if (count == h.getHeroParty().size()) {
            return "m";
        }
        count = 0;
        for (Monster monster: m.getMonsterParty()) {
            if (monster.getHP() <= 0) count++;
        }
        if (count == m.getMonsterParty().size()) {
            return "h";
        }
        return null;
    }
}
