package game;
public class Action{
    private String massege;
    private int intV;
    private char charV;
    private ItemAction itemAction;

    public void setItemAction (ItemAction itA){
        this.itemAction = itA;
    }
    public ItemAction getItemAction(){
        return this.itemAction;
    }

    public void setMessage(String msg){
        massege = msg;
    }
    public String getMessage (){
        return massege;
    }
    public void setIntValue(int v){
        intV = v;
    }
    public int getIntValue (){
        return intV;
    }
    public void setCharValue(char c){
        charV = c;
    }
    public char getCharValue (){
        return charV;
    }
}