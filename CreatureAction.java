package game;
public class CreatureAction extends Action{
    private String name;
    private String type;
    public CreatureAction(Creature owner){
        System.out.println("Constructing creature action of "+owner);
    }

    public void setName(String _name){
        name = _name;
    }
    public void setType(String _type){
        type = _type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}