public class Armor extends Item{

    private String name;
    private int room;
    private int serial;
    public static final String CLASSID = "Armor";
    public final char rep_char = ']';

    public Armor(String _name){
        //System.out.println("Create Armor, name: "+name);
        name = _name;
    }

    public void setName(String _name){
        //System.out.println("set Armor name "+ name);
        name = _name;
    }

    public void setID(int _room, int _serial){
        //System.out.println("set Armor room: "+ room + " serial: "+serial);
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
