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
    public boolean checkMove(int x, int y){ // place we need to go to
        /* Room indices as below
        posX,posY XXXXXX x+posX-1,y
            X                   X
            X                   X
            X                   X
        posX,posY+y-1 XXXX  x+posX-1,y+posY-1
         */
        // just make sure to return false when encountering any 'X
        if (x >this.getPosX() && x < this.getPosX()+width-1 && y > this.getPosY() && y < this.getPosY()+height-1){
            return true;
        }
        return false;

        //version 1
//        for (int i = this.getPosX(); i < this.getPosX() + width; i++){
//            if(i == x && (y == this.getPosY() || y == this.getPosY() + height -1)){
//                return false;
//            }
//        }
//        for (int i = this.getPosY(); i < this.getPosY() + height; i++){
//            if(i == y && (x == this.getPosX() || x == this.getPosX() + width -1)){
//                return false;
//            }
//        }

        //version 2
//        if(x == this.posX || (x == this.posX + this.width - 1) || y == this.posY || y == this.posY + height -1) {
//            return false;
//        }

        //return true;      //both
    }

    //Setters
    //Item mostRecentItem = currentRoom.getRecentItem(stepOnBlock,this.getPosX(),this.getPosY());
    public void dropCurrentItem(Item i){
        items.add(i);
    }
    public Item getRecentItem(Char ch, int x, int y){
        Item mostRecentItem = null;

        int count = 0;
        int currCount = -1;

        for (Item i : items){

            if (i.getrepr() == ch && i.getPosX() == x && i.getPosY() == y){
                currCount = count;
                mostRecentItem = i;
            }
            count ++;
        }
        if(currCount != -1) {
            items.remove(currCount);
        }
        return mostRecentItem;
    }
    public void setCreature(Creature creature){
        creatures.add(creature);
        System.out.println("setting creature in room ");
    }

    public void setItem(Item item){
        items.add(item);
        System.out.println("setting item in room ");
    }

    @Override
    public void SetWidth(int x) {
        this.width = x;
    }

    @Override
    public void SetHeight(int y) {
        this.height = y;
    }

    //Getters
    public ArrayList<Creature> getCreatures() {
        return creatures;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}