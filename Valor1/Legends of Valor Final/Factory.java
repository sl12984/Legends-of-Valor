import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class Factory {
	
	private Factory() {
		
	}
	
	public static ArrayList<Paladin> createPaladins(String path) throws Exception {
		ArrayList<Paladin> list = new ArrayList<Paladin>();
		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);
		String str = br.readLine();
		str = br.readLine();
		while (str != null) {
			String[] words = str.split("\\s+");
			list.add(new Paladin(words[0], Integer.parseInt(words[1]), 
					Integer.parseInt(words[2]), Integer.parseInt(words[3]), 
					Integer.parseInt(words[4]), Integer.parseInt(words[5]), 
					Integer.parseInt(words[6])));
			str = br.readLine();
		}
		return list;
	}
	
	public static ArrayList<Sorcerer> createSorcerers(String path) throws Exception {
		ArrayList<Sorcerer> list = new ArrayList<Sorcerer>();
		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);
		String str = br.readLine();
		str = br.readLine();
		while (str != null) {
			String[] words = str.split("\\s+");
			list.add(new Sorcerer(words[0], Integer.parseInt(words[1]), 
					Integer.parseInt(words[2]), Integer.parseInt(words[3]), 
					Integer.parseInt(words[4]), Integer.parseInt(words[5]), 
					Integer.parseInt(words[6])));
			str = br.readLine();
		}
		return list;
	}
	
	public static ArrayList<Warrior> createWarriors(String path) throws Exception {
		ArrayList<Warrior> list = new ArrayList<Warrior>();
		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);
		String str = br.readLine();
		str = br.readLine();
		while (str != null) {
			String[] words = str.split("\\s+");
			list.add(new Warrior(words[0], Integer.parseInt(words[1]), 
					Integer.parseInt(words[2]), Integer.parseInt(words[3]), 
					Integer.parseInt(words[4]), Integer.parseInt(words[5]), 
					Integer.parseInt(words[6])));
			str = br.readLine();
		}
		return list;
	}
	
	public static ArrayList<Dragon> createDragons(String path) throws Exception {
		ArrayList<Dragon> list = new ArrayList<Dragon>();
		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);
		String str = br.readLine();
		str = br.readLine();
		while (str != null) {
			String[] words = str.split("\\s+");
			list.add(new Dragon(words[0], Integer.parseInt(words[1]), 
					Integer.parseInt(words[2]), Integer.parseInt(words[3]), 
					Integer.parseInt(words[4])));
			str = br.readLine();
		}
		return list;
	}
	
	public static ArrayList<Exoskeleton> createExoskeletons(String path) throws Exception {
		ArrayList<Exoskeleton> list = new ArrayList<Exoskeleton>();
		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);
		String str = br.readLine();
		str = br.readLine();
		while (str != null) {
			String[] words = str.split("\\s+");
			list.add(new Exoskeleton(words[0], Integer.parseInt(words[1]), 
					Integer.parseInt(words[2]), Integer.parseInt(words[3]), 
					Integer.parseInt(words[4])));
			str = br.readLine();
		}
		return list;
	}
	
	public static ArrayList<Spirit> createSpirits(String path) throws Exception {
		ArrayList<Spirit> list = new ArrayList<Spirit>();
		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);
		String str = br.readLine();
		str = br.readLine();
		while (str != null) {
			String[] words = str.split("\\s+");
			list.add(new Spirit(words[0], Integer.parseInt(words[1]), 
					Integer.parseInt(words[2]), Integer.parseInt(words[3]), 
					Integer.parseInt(words[4])));
			str = br.readLine();
		}
		return list;
	}
	
	public static ArrayList<Armor> createArmors(String path) throws Exception {
		ArrayList<Armor> list = new ArrayList<Armor>();
		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);
		String str = br.readLine();
		str = br.readLine();
		while (str != null) {
			String[] words = str.split("\\s+");
			list.add(new Armor(words[0], Integer.parseInt(words[1]), 
					Integer.parseInt(words[2]), Integer.parseInt(words[3])));
			str = br.readLine();
		}
		return list;
	}
	
	public static ArrayList<FireSpell> createFireSpells(String path) throws Exception {
		ArrayList<FireSpell> list = new ArrayList<FireSpell>();
		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);
		String str = br.readLine();
		str = br.readLine();
		while (str != null) {
			String[] words = str.split("\\s+");
			list.add(new FireSpell(words[0], Integer.parseInt(words[1]), 
					Integer.parseInt(words[2]), Integer.parseInt(words[3]),
					Integer.parseInt(words[4])));
			str = br.readLine();
		}
		return list;
	}
	
	public static ArrayList<IceSpell> createIceSpells(String path) throws Exception {
		ArrayList<IceSpell> list = new ArrayList<IceSpell>();
		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);
		String str = br.readLine();
		str = br.readLine();
		while (str != null) {
			String[] words = str.split("\\s+");
			list.add(new IceSpell(words[0], Integer.parseInt(words[1]), 
					Integer.parseInt(words[2]), Integer.parseInt(words[3]),
					Integer.parseInt(words[4])));
			str = br.readLine();
		}
		return list;
	}
	
	public static ArrayList<LightningSpell> createLightningSpells(String path) throws Exception {
		ArrayList<LightningSpell> list = new ArrayList<LightningSpell>();
		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);
		String str = br.readLine();
		str = br.readLine();
		while (str != null) {
			String[] words = str.split("\\s+");
			list.add(new LightningSpell(words[0], Integer.parseInt(words[1]), 
					Integer.parseInt(words[2]), Integer.parseInt(words[3]),
					Integer.parseInt(words[4])));
			str = br.readLine();
		}
		return list;
	}
	
	public static ArrayList<Potion> createPotions(String path) throws Exception {
		ArrayList<Potion> list = new ArrayList<Potion>();
		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);
		String str = br.readLine();
		str = br.readLine();
		while (str != null) {
			String[] words = str.split("\\s+");
			list.add(new Potion(words[0], Integer.parseInt(words[1]), 
					Integer.parseInt(words[2]), Integer.parseInt(words[3]),
					words[4]));
			str = br.readLine();
		}
		return list;
	}
	
	public static ArrayList<Weapon> createWeapons(String path) throws Exception {
		ArrayList<Weapon> list = new ArrayList<Weapon>();
		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);
		String str = br.readLine();
		str = br.readLine();
		while (str != null) {
			String[] words = str.split("\\s+");
			list.add(new Weapon(words[0], Integer.parseInt(words[1]), 
					Integer.parseInt(words[2]), Integer.parseInt(words[3]),
					Integer.parseInt(words[4])));
			str = br.readLine();
		}
		return list;
	}
}
