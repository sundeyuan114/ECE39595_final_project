import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Player extends Creature implements playerObserver{

    private String name;
    private int room;
    private int serial;
    private ArrayList<Item> items = new ArrayList<Item>();
    private Armor armor = null;
    private Sword sword = null;
    public static final String CLASSID = "Player";
    public final char rep_char = '@';

    public Player(String _name, int _room, int _serial){
        //System.out.println("Constructing Player called: "+name+" in room "+ room);
        name = _name;
        room = _room;
        serial = _serial;
    }

    public void setWeapon(Item _sword){
        //System.out.println("Player set weapon");
        items.add(_sword);
        sword = (Sword) _sword;
    }

    public void setArmor(Item _armor){
        //System.out.println("Player set armor");
        items.add(_armor);
        armor = (Armor) _armor;
    }

    public Item drop(int index) throws IndexOutOfBoundsException{
        Item temp = items.get(index);
        if(temp == armor){
            armor = null;
        }
        if(temp == sword){
            sword = null;
        }
        items.remove(index);
        return temp;
    }

    public String getCLASSID(){
        return CLASSID;
    }

    public char getRep_char(){
        return rep_char;
    }

    public ArrayList<Item> getItems(){
        return items;
    }

    @Override
    public void playerUpdate(char inputChar) {
        if(inputChar == 'h'){
            posX.set(0,posX.get(0)-1);
        }else if(inputChar == 'j'){
            posY.set(0,posY.get(0)-1);
        }else if(inputChar == 'k'){
            posY.set(0,posY.get(0)+1);
        }else if(inputChar == 'l'){
            posX.set(0,posX.get(0)+1);
        }
    }


}
