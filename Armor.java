package game;
public class Armor extends Item{
    private int roomNumb;
    private int serial;
    private Room room;
    public Armor(String _name){
        super.setName(_name);
    }

    public void setID(int _room,int _serial){
        roomNumb = _room;
        serial=_serial;
        System.out.println("Setting Armor room: " +_room+"serial: "+_serial);
    }

    public void SetRoom(Room _room){
        room = _room;
    }

    public void SetPosX(int posX) {
        this.posX = posX + room.getPosX();
    }

    public void SetPosY(int posY) {
        this.posY = posY + room.getPosY() + 2;
    }
}