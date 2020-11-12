import java.util.ArrayList;

public class Room extends Structure{

    private int ID;
    private ArrayList<Creature> monsters = new ArrayList<Creature>();
    public static final String CLASSID = "Room";

    public Room(int _ID){
        //System.out.println("build room with ID: "+str);
        ID = _ID;
    }

    public void setID(int _ID){
        //System.out.println("set roomID = "+ room);
        ID = _ID;

    }

    public void setCreature(Creature monster){
        //System.out.println("add monster to this room");
        monsters.add(monster);
    }

    public String getCLASSID(){
        return CLASSID;
    }

    public char getRep_char(){
        return rep_char;
    }
}
