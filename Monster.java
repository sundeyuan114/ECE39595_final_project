package game;
public class Monster extends Creature{
    private Room room;
    private Char repr;


    public Monster(){
        System.out.println("Constructing monster object");
    }
    public void setName(String _name){
        System.out.println(_name);
        super.name = _name;
        System.out.println("Setting name to be "+_name);
    }

    public void setID(int room, int serial){
        System.out.println("Setting room to "+ room+", serial number "+ serial);
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

    @Override
    public String getName(){
        return name;
    }


    @Override
    public Char getrepr(){
        return this.repr;
    }

    public void setrepr(String _name){
        if (name.equalsIgnoreCase("Troll")){
            repr = new Char('T');
        }
        else if (name.equalsIgnoreCase("Snake")){
            repr = new Char('S');
        }
        else if (name.equalsIgnoreCase("Hobgoblin"))
        {
            repr = new Char('H');
        }
        else{
            System.out.println("Monster repr return error");
            repr = new Char('~');
        }
    }
}