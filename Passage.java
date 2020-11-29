package game;
import java.util.ArrayList;
import java.util.Queue;
import java.util.*;

public class Passage extends Structure{
    private int room1;
    private int room2;
    private Queue<Integer> posx = new LinkedList();
    private Queue<Integer> posy = new LinkedList();

    private ArrayList<Integer> Aposx = new ArrayList<Integer>();
    private ArrayList<Integer> Aposy = new ArrayList<Integer>();

    public ArrayList<Integer> getArrayX (){
        return Aposx;
    }
    public ArrayList<Integer> getArrayY (){
        return Aposy;
    }
    public Passage(){
        System.out.println("Constructing passage");
    }

    public void setName(String _name){
        System.out.println("Setting passage name to be "+_name);
    }

    public void setID(int _room1,int _room2){
        room1 = _room1;
        room2 = _room2;
        System.out.println("Setting passage room 1 = "+_room1+", passage room 2 = "+_room2);
    }
    public int getsize(){
        return this.posx.size();
    }

    @Override
    public void SetPosX(int x){
        posx.add(x);
        Aposx.add(x);
    }
    @Override
    public void SetPosY(int y){
        posy.add(y);
        Aposy.add(y);
    }

    public int GetPosX(){
        if(!posx.isEmpty()) {
            return posx.poll();}
        else{
            return -1;// -1 should never exist. Since all number are positive. This is a bug.
        }
    }
    public int GetPosY() {
        if (!posy.isEmpty()) {
            return posy.poll();
        } else {
            return -1;// -1 should never exist. Since all number are positive. This is a bug.
        }
    }

    //called by dungeon
    @Override
    public boolean checkMove(int x, int y){
        /* Passage indices as below
        x1,y1++++++++++++++ x2,y1
                              +
                              +
                              +
                             x2,y2+++++++ x3,y2
         */
         //just make sure to return false when encountering any '+'
        for (int i = 0; i < Aposx.size()-1; i++){
            // we're looking at posx/y[i] and posx/y[i+1]
            if (Aposx.get(i).equals(Aposx.get(i + 1))){
                if (Aposy.get(i) > Aposy.get(i + 1)){ // this means it's vertical, check if x,y is inside this bar
                    if (y <= Aposy.get(i) && y >= Aposy.get(i + 1) && x == Aposx.get(i)){
                        return true;
                    }
                }
                else{
                    if (y <= Aposy.get(i + 1) && y >= Aposy.get(i) && x == Aposx.get(i)){
                        return true;
                    }
                }
            }
            else if (Aposy.get(i).equals(Aposy.get(i + 1))){ // this means it's horizontal
                if (Aposx.get(i) < Aposx.get(i + 1)){
                    if (x <= Aposx.get(i + 1) && x >= Aposx.get(i) && y == Aposy.get(i)){
                        return true;
                    }
                }
                else {
                    if (x >= Aposx.get(i + 1) && x <= Aposx.get(i) && y == Aposy.get(i)){
                        return true;
                    }
                }
            }
            else {
                System.out.println("Bug in passage checkmove");
            }
        }


        return false;

    }
}