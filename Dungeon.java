package game;
import java.util.*;
public class Dungeon {

    private String name;
    private int width;
    private int topHeight;
    private int gameHeight;
    private int bottomHeight;

    private ArrayList<Room> rooms = new ArrayList<Room>();
    private ArrayList<Creature> creatures = new ArrayList<Creature>();
    private ArrayList<Passage> passages = new ArrayList<Passage>();
    private ArrayList<Item> items = new ArrayList<Item>();
    private Player player = null;//add player

    // Constructor
    public Dungeon(String _name, int wid, int topHeit, int gameHeit, int bottomHeit) {
        name = _name;
        width = wid;
        topHeight = topHeit;
        gameHeight = gameHeit;
        bottomHeight = bottomHeit;
        System.out.println("Constructing Dungeon " + name);
    }

    // called by player
    public boolean validateMove(int x,int y){
        ArrayList<Structure> structs = new ArrayList<Structure>();
        structs.addAll(rooms);
        structs.addAll(passages);

        for (Structure struct: structs){
            if (struct.checkMove(x,y)){
                return true;
            }
        }
        return false;
    }



    public void addRoom(Room r) {
        rooms.add(r);
        System.out.println("Adding room  " + r);
    }


    public void addCreature(Creature c) {
        creatures.add(c);
        System.out.println("Adding creature" + c);
    }

    public void addPassage(Passage p) {
        passages.add(p);
        System.out.println("Adding passage " + p);
    }

    public void addItem(Item i) {
        items.add(i);
        System.out.println("Adding item " + i);
    }

    //getters
    public Dungeon getDungeon(String _name, int width, int gameHeight) {
        System.out.println("Getting Dungeon" + _name + "width " + width + " gameHeight " + gameHeight);
        return this;
    }

    public ArrayList<Creature> getCreatures() {
        return creatures;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public ArrayList<Passage> getPassages() {
        return passages;
    }

    public String getName() {
        return name;
    }

    public int getWidth() {
        return width;
    }

    public int getGameHeight() {
        return gameHeight;
    }

    public int getTopHeight() {
        return topHeight;
    }

    public int getBottomHeight() {
        return bottomHeight;
    }

    public Player getPlayer() {
        return player;
    }


    //Setters
    public void setName(String s) {
        name = s;
    }

    public void setWidth(int w) {
        width = w;
    }

    public void setGameHeight(int g) {
        gameHeight = g;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}