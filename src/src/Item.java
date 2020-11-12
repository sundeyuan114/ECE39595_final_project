public class Item extends Displayable{

    private Displayable owner;
    protected String name;

    public void setOwner(Creature _owner){
        owner = _owner;
    }

    public String getName(){//place holder
        return name;
    }
}
