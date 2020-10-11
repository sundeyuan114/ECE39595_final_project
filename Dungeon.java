package src;
public class Dungeon{

    private String name;
    private int width;
    private int topHeight;
    private int gameHeight;
    private int bottomHeight;

    public Dungeon(String _name,int wid,int topHeit,int gameHeit,int bottomHeit){
        name = _name;
        width = wid;
        topHeight = topHeit;
        gameHeight = gameHeit;
        bottomHeight = bottomHeit;
    }

    public Dungeon getDungeon(String _name, int width, int gameHeight){
        System.out.println("Constructing Dungeon"+_name+"width "+width+" gameHeight "+gameHeight);
        return this;
    }

    public void addRoom(Room room){
        System.out.println("Adding room + " room);
    }

    public void addCreature(Creature creature){
        System.out.println("Adding creature" + creature);
    }

    public void addPassage(Passage passage){
        System.out.println("Adding passage " + passage);
    }

    public void addItem(Item item){
        System.out.println("Adding item "+ item);
    }
}