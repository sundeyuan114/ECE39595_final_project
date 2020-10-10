public class Dungeon{

    public Dungeon getDungeon(String _name, int width, int gameHeight){
        System.out.println("Constructing ");
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