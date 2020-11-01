package game;
public class Armor extends Item{
    int room;
    int serial;
    public Armor(String _name){
        super.setName(_name);
    }

    public void setID(int _room,int _serial){
        room = _room;
        serial=_serial;
        System.out.println("Setting Armor room: " +_room+"serial: "+_serial);
    }
}