public class Rule {

    public static int dodgeChance(int agility) {
        return agility / 500;
    }

    public static int weaponDamage(int strength, int weaponDamage) {
        return (int) ((strength + weaponDamage) / 20);
    }

    public static int goldGain(int level) {
        return level * 100;
    }

    public static int expGain(int num) {
        return num * 2;
    }

    public static int skillLoss(int skill) {
        return skill / 10;
    }


}
