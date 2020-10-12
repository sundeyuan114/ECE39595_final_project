public class Armor extends Item{
    String name;
    int room;
    int serial;
    public Armor(String _name){
        name = _name;
        System.out.println("Constructing armor object "+_name);
    }

    public void setName(String _name){
        name = _name;
        System.out.println("Setting Armor name: "+_name);
    }
    public void setID(int _room,int _serial){
        room = _room;
        serial=_serial;
        System.out.println("Setting Armor room: " +_room+"serial: "+_serial);
    }
}