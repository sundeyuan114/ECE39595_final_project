public class Monster extends Creature{
    public Monster(){
        System.out.println("Constructing monster object");
    }

    public void setName(String _name){
        System.out.println("Setting name to be "+_name);
    }
    public void setID(int room, int serial){
        System.out.println("Setting room to "+ room+", serial number "+ serial);
    }
}