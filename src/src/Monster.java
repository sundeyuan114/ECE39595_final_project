public class Monster extends Creature{

    private String name;
    private int room;
    private int serial;
    public static final String CLASSID = "Monster";
    public char rep_char;

    public Monster(){
        //System.out.println("construct new monster");
    }

    public void setName(String _name){
        //System.out.println("This monster is called "+name);
        name = _name;
        if(name.equals("Troll")){
            rep_char = 'T';
        }else if(name.equals("Hobgoblin")){
            rep_char = 'H';
        }else if(name.equals("Snake")){
            rep_char = 'S';
        }else{
            System.out.println("Error: unknown monster name: "+ name);
        }
    }

    public void setID(int _room, int _serial){
        //System.out.println("Monster's Room: " +room + " Serial: " + serial);
        room = _room;
        serial = _serial;
    }

    public String getCLASSID(){
        return CLASSID;
    }

    public char getRep_char(){
        return rep_char;
    }
}
