public class Potion extends Item {
	public final static int MAX_BUFF = 10000;
	public final static int MAX_ATTR_NUM = 5;
	private int buff;
	private int[] attr;
	private int attrNum;
	
	//attr string is in the form "attr1/attr2/.../attr5"
	public Potion(String name, int price, int level, int buff, String attr) {
		super(name, price, level);
		if (buff < 0 || buff > MAX_BUFF || attr == null) {
			throw new IllegalArgumentException();
		}
		this.buff = buff;
		this.attr = new int[MAX_ATTR_NUM];
		attrNum = 0;
		String[] words = attr.split("/");
		for (int i=0; i<words.length; i++) {
			if (i>=5) {
				break;
			}
			if (words[i].equals("Health")) {
				this.attr[i] = Attributes.HP;
				attrNum++;
			} else if (words[i].equals("Mana")) {
				this.attr[i] = Attributes.MP;
				attrNum++;
			} else if (words[i].equals("Strength")) {
				this.attr[i] = Attributes.STRENGTH;
				attrNum++;
			} else if (words[i].equals("Dexterity")) {
				this.attr[i] = Attributes.DEXTERITY;
				attrNum++;
			} else if (words[i].equals("Agility")) {
				this.attr[i] = Attributes.AGILITY;
				attrNum++;
			} else {
				throw new IllegalArgumentException();
			}
		}
		
	}
	
	public int getBuff() {
		return buff;
	}
	
	public int getAttr(int i) {
		return attr[i];
	}
	
	public int getAttrNum() {
		return attrNum;
	}

    @Override
    public void print() {
		super.print();
		System.out.println("Description: ");
		for (int i=0; i<attrNum; i++) {
			System.out.println("Increases a hero's " + Attributes.getName(attr[i]) + 
					" by " + buff + ". ");
		}
	}
}
