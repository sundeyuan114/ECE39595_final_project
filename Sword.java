package game;
public class Sword extends Item{
    private Room room;
    public Sword(String _name){
        super.setName(_name);
    }

    public void setID(int room,int serial){
        System.out.println("Setting sword room"+room+ ", serial "+serial);
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