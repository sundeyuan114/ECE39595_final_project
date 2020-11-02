package game;
public class Monster extends Creature{
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

    @Override
    public String getName(){
        return name;
    }

    @Override
    public Char getrepr(){
        if (name.equalsIgnoreCase("Troll")){
            return new Char('T');
        }
        else if (name.equalsIgnoreCase("Snake")){
            return new Char('S');
        }
        else if (name.equalsIgnoreCase("Hobgoblin"))
        {
            return new Char('H');
        }
        else{
            System.out.println("Monster repr return error");
            return new Char('~');
        }
    }
}