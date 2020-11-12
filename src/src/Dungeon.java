import java.util.ArrayList;

//stand alone class for now
public class Dungeon {

    private String name;
    private int width;
    private int height;
    private ArrayList<Displayable> displayables = new ArrayList<Displayable>();


    public Dungeon(String _name, int _width, int _gameHeight){
        //System.out.println("construct dungeon with name: "+name+" width: "+width+" gameHeight: "+gameHeight);
        name = _name;
        width = _width;
        height = _gameHeight;
    }

    public void addRoom(Room room){
        //System.out.println("Add room to this Dungeon");
        displayables.add(room);
    }

    public void addCreature(Creature creature){
        //System.out.println("add creature to this dungeon");
        displayables.add(creature);
    }

    public void addPassage(Passage passage){
        //System.out.println("add passage to this dungeon");
        displayables.add(passage);
    }

    public void addItem(Item item){
        //System.out.println("add item to this dungeon");
        displayables.add(item);
    }

}
