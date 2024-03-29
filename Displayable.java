package game;
public class Displayable{
    protected int posX;
    protected int posY;
    private int intValue;
    protected CreatureAction CAction;

    public void setItemAction (ItemAction i){
    }
    public Displayable(){
        System.out.println("Constructing Displayable");
    }

    public ItemAction getItemAction (){
        return null;
    }

    public void setInvisible(){
        System.out.println("Setting invisible ");
    }

    public void setVisible(){
        System.out.println("Setting visible");
    }

    public void setMaxHit(int maxHit){
        System.out.println("setting maxhit = "+ maxHit);
    }

    public void setHpMove(int hpMoves){
        System.out.println("Setting hpmoves " + hpMoves);
    }

    public void setHp(int Hp){
        System.out.println("Setting Hp "+Hp);
    }

    public void setType(char t){
        System.out.println("Setting type = "+ t);
    }

    public void setIntValue(int v){
        intValue = v;
    }
    public int getIntValue(){
        return intValue;
    }

    public void SetPosX(int x){
        posX = x;
    }

    public void SetPosY(int y ){
        posY = y;
    }

    public void SetWidth(int x){
        System.out.println("Setting width = "+ x);
    }

    public  void SetHeight(int y){
        System.out.println("Setting height = "+y);
    }


    public int getPosX() {
        return posX;
    }
    public int getPosY() {
        return posY;
    }

    public void setCreatureAction(CreatureAction CAction) {
        this.CAction = CAction;
    }

    public CreatureAction getCreatureAction(){
        return this.CAction;
    }
}