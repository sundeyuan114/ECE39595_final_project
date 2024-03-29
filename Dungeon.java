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

    public void removeCreature(Creature cr){
        creatures.remove(cr);
    }

    public Room findCurrentRoom (int x, int y1){
        //change absolute position to relative position.
        int y = y1-2;
        for (Room currentRoom : rooms){
            System.out.println("Room X Y"+ currentRoom.getPosX()+" "+currentRoom.getPosY());
            if(x > currentRoom.getPosX() && x < currentRoom.getPosX() + currentRoom.getWidth()-1) {
                if(y > currentRoom.getPosY() && y < currentRoom.getPosY() + currentRoom.getHeight()-1){
                    return currentRoom;
                }
            }
        }
        return null;
    }
    // called by player
    public boolean validateMove(int x,int y){
//        ArrayList<Structure> structs = new ArrayList<Structure>();
//        structs.addAll(rooms);
//        structs.addAll(passages);

        for (Passage p : passages){
            if (p.checkMove(x,y-2)){            // IF BUG STEP 3/4 LOOK HERE!!!!!!!!!!!!!!
                System.out.println("true");
                return true;                  // we shoudln't actually do y-2,
                // but instead change room and passages to display at y+2
            }
        }
        for (Room r : rooms){
            if (r.checkMove(x,y-2)){            // IF BUG STEP 3/4 LOOK HERE!!!!!!!!!!!!!!
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