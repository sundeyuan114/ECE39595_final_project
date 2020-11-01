package game;
import java.util.*;
public class Room extends Structure{
    int ID;
    int width;
    int height;
    private ArrayList<Item> items = new ArrayList<Item>();
    private ArrayList<Creature> creatures = new ArrayList<Creature>();

    public Room(String _name){
        ID = Integer.parseInt(_name);       //name is ID, as it can only be number in xml
        System.out.println("setting room ID "+ _name);
    }
    //called by dungeon
    public boolean checkMove(int x, int y){ // 我们需要移动到的位置，
        /* Room indices as below
        posX,posY XXXXXX x+posX-1,y
            X                   X
            X                   X
            X                   X
        posX,posY+y-1 XXXX  x+posX-1,y+posY-1
         */
        // just make sure to return false when encountering any 'X

        for (int i = this.getPosX(); i < this.getPosX() + width; i++){
            if(i == x && (y == this.getPosY() || y == this.getPosY() + height -1)){
                return false;
            }
        }
        for (int i = this.getPosY(); i < this.getPosY() + height; i++){
            if(i == y && (x == this.getPosX() || x == this.getPosX() + width -1)){
                return false;
            }
        }

        return true;
    }

    //Setters
    public void setCreature(Creature creature){
        creatures.add(creature);
        System.out.println("setting creature in room ");
    }

    public void setItem(Item item){
        items.add(item);
        System.out.println("setting item in room ");
    }

    //Getters
    public ArrayList<Creature> getCreatures() {
        return creatures;
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}