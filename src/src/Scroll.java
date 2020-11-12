public class Scroll extends Item{

    public static final String CLASSID = "Scroll";
    public final char rep_char = '?';

    public Scroll(String name){
        //System.out.println("Create scroll, set name: "+name);
    }

    public void setID(int room, int serial){
        //System.out.println("set Scroll room: "+ room + " serial: "+serial);
    }

    public String getCLASSID(){
        return CLASSID;
    }

    public char getRep_char(){
        return rep_char;
    }
}
