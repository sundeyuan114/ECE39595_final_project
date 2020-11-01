package game;
public class Monster extends Creature{
    public Monster(){
        System.out.println("Constructing monster object");
    }
    private String type;
    public void setName(String _name){
        type = _name;
        System.out.println("Setting name to be "+_name);
    }
    public void setID(int room, int serial){
        System.out.println("Setting room to "+ room+", serial number "+ serial);
    }
    public Char getrepr(){
        if (type.equalsIgnoreCase("Troll")){
            return new Char('T');
        }
        else if (type.equalsIgnoreCase("Snake")){
            return new Char('S');
        }
        else if (type.equalsIgnoreCase("Hobgoblin"))
        {
            return new Char('H');
        }
        else{
            System.out.println("Monster repr return error");
            return new Char('~');
        }
    }
}