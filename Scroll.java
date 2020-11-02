package game;
public class Scroll extends Item{
    private Room room;

    public Scroll(String _name){
        super.setName(_name);
    }

    public void setID(int room, int serial){
        System.out.println("Setting scroll to room"+room+", serial"+serial);
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