//this is a base class
//children: CreatureAction, ItemAction
public class Action {

    protected Creature owner;
    protected String name;
    protected String type;
    protected String message;
    protected int intValue;
    protected char charValue;
    protected Displayable victim;

    public void setVictim(Displayable disp){
        victim = victim;
    }

    public void setMessage(String msg){
        //System.out.println("set message: "+msg);
        message = msg;
    }

    public void setIntValue(int v){
        //System.out.println("set int value: "+v);
        intValue = v;
    }

    public void setCharValue(char c){
        //System.out.println("set char value: "+c);
        charValue = c;
    }

    public void setName(String _name){
        name = _name;
        //System.out.println("Action set name: "+name);
    }

    public void setType(String _type){
        type = _type;
        //System.out.println("Action set type: "+type);
    }

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }

    public String getMessage(){
        return message;
    }

    public int getIntValue(){
        return intValue;
    }

}
