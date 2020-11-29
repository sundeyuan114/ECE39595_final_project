package game;
public class ItemAction extends Action{
    private String name;
    private String type;
    public ItemAction(Item owner){
        System.out.println("Constructing "+owner+"'s item action.");
    }

    public void setName(String _name){
        name = _name;
    }
    public void setType(String _type){
        type = _type;
    }
    public String getName (){
        return name;
    }
    public String getType (){
        return type;
    }
}