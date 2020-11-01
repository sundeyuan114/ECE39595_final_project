package game;
public class Item extends Displayable{
    private String name;

    public void setOwner(Creature owner){
        System.out.println("Setting owner to "+ owner);
    }
    public void setName(String _name){
        name = _name;
    }

    public String getName() {
        return name;
    }

    public Char getrepr(){
        if (name.equals("Sword")){
            return new Char(')');
        }
        if (name.equals("Armor")){
            return new Char(']');
        }
        if (name.equals("Scroll")){
            return new Char('?');
        }
        return new Char('~');
    }

}