public class Sword extends Item{

    private String name;
    private int room;
    private int serial;
    private static final String CLASSID = "Sword";
    public final char rep_char = ')';

    public Sword(String _name){
        //System.out.println("Create Sword, name: "+ name);
        name = _name;
    }

    public void setID(int _room, int _serial){
        //System.out.println("set Sword room: "+ room + " serial: "+serial);
        room = _room;
        serial = _serial;
    }

    public String getCLASSID(){
        return CLASSID;
    }

    public char getRep_char(){
        return rep_char;
    }

    public String getName(){
        return name;
    }

}
