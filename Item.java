package game;
public class Item extends Displayable{
    private String name;
    protected ItemAction iAction;

    @Override
    public void setItemAction (ItemAction a){
        iAction = a;
    }

    @Override
    public ItemAction getItemAction (){
        return iAction;
    }

    public void setOwner(Creature owner){
        System.out.println("Setting owner to "+ owner);
    }
    public void setName(String _name){
        if (_name.contains("Sword")){
            name = "Sword";
        }    else    if (_name.contains("Armor")){
            name = "Armor";
        }
        else    if (_name.contains("Scroll")){
            name = "Scroll";
        }
        
    }

    public String getName() {
        return name;
    }

    public Char getrepr(){
        if (name.equals("Sword")){
            return new Char(')');
        }
        else if (name.contains("Sword")){
            return new Char(')');
        }
        else if (name.contains("Armor")){
            return new Char(']');
        }
        else if (name.equals("Armor")){
            return new Char(']');
        }
        else if (name.equals("Scroll")){
            return new Char('?');
        }
        else if (name.contains("Scroll")){
            return new Char('?');
        }
        else
        {
            System.out.println("item debug"+name);
            return new Char ('!');
        }
    }

}