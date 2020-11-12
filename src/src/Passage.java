public class Passage extends Structure{

    private String name;
    private int room1;
    private int room2;
    public static final String CLASSID = "Passage";

    public Passage(){

    }

    public void setName(String _name){
        name = _name;
    }

    public void setID(int _room1, int _room2){
        //System.out.println("Set passage room1: "+ room1 + " room2: "+room2);
        room1 = _room1;
        room2 = _room2;
    }

    public String getCLASSID(){
        return CLASSID;
    }

}
