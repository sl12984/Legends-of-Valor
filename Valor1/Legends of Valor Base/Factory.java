import java.nio.file.Files;
import java.nio.file.Path;

public class Factory {
	
	private Factory() {
		
	}
	
	public static String readPath(String path) throws Exception {
		return Files.readString(Path.of(path).toAbsolutePath());
	}
	
	public static Paladin[] createPaladins(String path) throws Exception {
		String[] lines = readPath(path).split("\n");
		Paladin[] list = new Paladin[lines.length-1];
		for (int i=1; i<lines.length; i++) {
			String[] words = lines[i].split("\\s+");
			list[i-1] = new Paladin(words[0], Integer.parseInt(words[1]), 
					Integer.parseInt(words[2]), Integer.parseInt(words[3]), 
					Integer.parseInt(words[4]), Integer.parseInt(words[5]), 
					Integer.parseInt(words[6]));
		}
		return list;
	}
	
	public static Sorcerer[] createSorcerers(String path) throws Exception {
		String[] lines = readPath(path).split("\n");
		Sorcerer[] list  = new Sorcerer[lines.length-1];
		for (int i=1; i<lines.length; i++) {
			String[] words = lines[i].split("\\s+");
			list[i-1] = new Sorcerer(words[0], Integer.parseInt(words[1]), 
					Integer.parseInt(words[2]), Integer.parseInt(words[3]), 
					Integer.parseInt(words[4]), Integer.parseInt(words[5]), 
					Integer.parseInt(words[6]));
		}
		return list;
	}
	
	public static Warrior[] createWarriors(String path) throws Exception {
		String[] lines = readPath(path).split("\n");
		Warrior[] list  = new Warrior[lines.length-1];
		for (int i=1; i<lines.length; i++) {
			String[] words = lines[i].split("\\s+");
			list[i-1] = new Warrior(words[0], Integer.parseInt(words[1]), 
					Integer.parseInt(words[2]), Integer.parseInt(words[3]), 
					Integer.parseInt(words[4]), Integer.parseInt(words[5]), 
					Integer.parseInt(words[6]));
		}
		return list;
	}
	
	public static Dragon[] createDragons(String path) throws Exception {
		String[] lines = readPath(path).split("\n");
		Dragon[] list  = new Dragon[lines.length-1];
		for (int i=1; i<lines.length; i++) {
			String[] words = lines[i].split("\\s+");
			list[i-1] = new Dragon(words[0], Integer.parseInt(words[1]), 
					Integer.parseInt(words[2]), Integer.parseInt(words[3]), 
					Integer.parseInt(words[4]));
		}
		return list;
	}
	
	public static Exoskeleton[] createExoskeletons(String path) throws Exception {
		String[] lines = readPath(path).split("\n");
		Exoskeleton[] list  = new Exoskeleton[lines.length-1];
		for (int i=1; i<lines.length; i++) {
			String[] words = lines[i].split("\\s+");
			list[i-1] = new Exoskeleton(words[0], Integer.parseInt(words[1]), 
					Integer.parseInt(words[2]), Integer.parseInt(words[3]), 
					Integer.parseInt(words[4]));
		}
		return list;
	}
	
	public static Spirit[] createSpirits(String path) throws Exception {
		String[] lines = readPath(path).split("\n");
		Spirit[] list  = new Spirit[lines.length-1];
		for (int i=1; i<lines.length; i++) {
			String[] words = lines[i].split("\\s+");
			list[i-1] = new Spirit(words[0], Integer.parseInt(words[1]), 
					Integer.parseInt(words[2]), Integer.parseInt(words[3]), 
					Integer.parseInt(words[4]));
		}
		return list;
	}
	
	public static Armor[] createArmors(String path) throws Exception {
		String[] lines = readPath(path).split("\n");
		Armor[] list  = new Armor[lines.length-1];
		for (int i=1; i<lines.length; i++) {
			String[] words = lines[i].split("\\s+");
			list[i-1] = new Armor(words[0], Integer.parseInt(words[1]), 
					Integer.parseInt(words[2]), Integer.parseInt(words[3]));
		}
		return list;
	}
	
	public static FireSpell[] createFireSpells(String path) throws Exception {
		String[] lines = readPath(path).split("\n");
		FireSpell[] list  = new FireSpell[lines.length-1];
		for (int i=1; i<lines.length; i++) {
			String[] words = lines[i].split("\\s+");
			list[i-1] = new FireSpell(words[0], Integer.parseInt(words[1]), 
					Integer.parseInt(words[2]), Integer.parseInt(words[3]),
					Integer.parseInt(words[4]));
		}
		return list;
	}
	
	public static IceSpell[] createIceSpells(String path) throws Exception {
		String[] lines = readPath(path).split("\n");
		IceSpell[] list  = new IceSpell[lines.length-1];
		for (int i=1; i<lines.length; i++) {
			String[] words = lines[i].split("\\s+");
			list[i-1] = new IceSpell(words[0], Integer.parseInt(words[1]), 
					Integer.parseInt(words[2]), Integer.parseInt(words[3]),
					Integer.parseInt(words[4]));
		}
		return list;
	}
	
	public static LightningSpell[] createLightningSpells(String path) throws Exception {
		String[] lines = readPath(path).split("\n");
		LightningSpell[] list  = new LightningSpell[lines.length-1];
		for (int i=1; i<lines.length; i++) {
			String[] words = lines[i].split("\\s+");
			list[i-1] = new LightningSpell(words[0], Integer.parseInt(words[1]), 
					Integer.parseInt(words[2]), Integer.parseInt(words[3]),
					Integer.parseInt(words[4]));
		}
		return list;
	}
	
	public static Potion[] createPotions(String path) throws Exception {
		String[] lines = readPath(path).split("\n");
		Potion[] list  = new Potion[lines.length-1];
		for (int i=1; i<lines.length; i++) {
			String[] words = lines[i].split("\\s+");
			list[i-1] = new Potion(words[0], Integer.parseInt(words[1]), 
					Integer.parseInt(words[2]), Integer.parseInt(words[3]),
					words[4]);
		}
		return list;
	}
	
	public static Weapon[] createWeapons(String path) throws Exception {
		String[] lines = readPath(path).split("\n");
		Weapon[] list  = new Weapon[lines.length-1];
		for (int i=1; i<lines.length; i++) {
			String[] words = lines[i].split("\\s+");
			list[i-1] = new Weapon(words[0], Integer.parseInt(words[1]), 
					Integer.parseInt(words[2]), Integer.parseInt(words[3]),
					Integer.parseInt(words[4]));
		}
		return list;
	}
}
